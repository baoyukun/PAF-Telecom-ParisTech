<?php

$t=time();

$data=json_decode(file_get_contents("data/keywordstime.json"))->results->bindings;
$l=count($data);
for ($i=0; $i<$l; $i++){
    if (!property_exists($data[$i],"month")){
        $data[$i]->month=new stdClass();
        $data[$i]->month->value="July";
    }
    $data[$i]->year->value=(int)$data[$i]->year->value;
    if ($data[$i]->year->value<2000){
        unset($data[$i]);
        break;
    }
    switch(strtolower($data[$i]->month->value)){
        case "january": $data[$i]->month->value=1;
        case "february": $data[$i]->month->value=2;
        case "march": $data[$i]->month->value=3;
        case "april": $data[$i]->month->value=4;
        case "may": $data[$i]->month->value=5;
        case "june": $data[$i]->month->value=6;
        case "july": $data[$i]->month->value=7;
        case "august": $data[$i]->month->value=8;
        case "september": $data[$i]->month->value=9;
        case "october": $data[$i]->month->value=10;
        case "november": $data[$i]->month->value=11;
        case "december": $data[$i]->month->value=12;
    }
    $data[$i]->month->value=(int)$data[$i]->month->value;
}
$data=array_values($data);

$t=time()-$t;
echo "init done in $t seconds<br>";ob_flush();flush();
$t=time();

$keywords=[];
foreach ($data as $d){
    $k=$d->keyword->value;
    if (!array_key_exists($k,$keywords)){
        $keywords[$k]=1;
    }
    else {
        $keywords[$k]++;
    }
}
$bestkws=[];
for ($i=0; $i<5; $i++){
    $bestk=array_search(max($keywords),$keywords);
    $bestkws[]=$bestk;
    unset($keywords[$bestk]);
}
$l=count($data);
for ($i=0; $i<$l; $i++){
    if (!in_array($data[$i]->keyword->value,$bestkws)){
        unset($data[$i]);
    }
}
$data=array_values($data);


$t=time()-$t;
echo "cleaning done in $t seconds<br>";ob_flush();flush();
$t=time();

$datemin=2012;
$datemax=0001;
foreach ($data as $d){
    $date=100*($d->year->value%100)+$d->month->value;
    if ($date<$datemin){
        $datemin=$date;
        $yearmin=$d->year->value;
        $monthmin=$d->month->value;
    }
    if ($date>$datemax){
        $datemax=$date;
        $yearmax=$d->year->value;
        $monthmax=$d->month->value;
    }
}
$dates=[];
for ($month=$monthmin; $month<13; $month++){
    $dates[]=100*($yearmin%100)+$month;
}
for ($year=$yearmin+1; $year<$yearmax; $year++){
    for ($month=1; $month<13; $month++){
        $dates[]=100*($year%100)+$month;
    }
}
for ($month=1; $month<=$monthmax; $month++){
    $dates[]=100*($yearmax%100)+$month;
}

$t=time()-$t;
echo "dates created in $t seconds<br>";ob_flush();flush();
$t=time();

$trends=[];
foreach ($dates as $d){
    $trends[$d]=[];
}
$keywords=[];
foreach ($data as $d){
    $date=100*($d->year->value%100)+$d->month->value;
    $k=$d->keyword->value;
    if (array_key_exists($k,$trends[$date])){
        $trends[$date][$k]++;
    }
    else {
        $trends[$date][$k]=1;
    }
    if (!in_array($k,$keywords)){
        $keywords[]=$k;
    }
}

$t=time()-$t;
echo "trends built in $t seconds<br>";ob_flush();flush();
$t=time();

$txt="key,value,date\r\n";
foreach ($keywords as $k){
    foreach ($dates as $d){
        if (array_key_exists($k,$trends[$d])){
            $txt.=$k.",".$trends[$d][$k].",".($d%100)."/01/".(floor($d/100))."\r\n";
        }
        else {
            $txt.=$k.",0,".($d%100)."/01/".(floor($d/100))."\r\n";
        }
    }
}
$f=fopen("data/timekeywords.csv","w");
fwrite($f,$txt);
fclose($f);

echo "<h1>done</h1>";
?>






















