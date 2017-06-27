<?php

$data=json_decode(file_get_contents("data/keywordstime.json"))->results->bindings;

// format Month => month number
for ($i=0; $i<count($data); $i++){
    switch($data[$i]->month->value){
        case "January": $data[$i]->month->value=01;
        case "February": $data[$i]->month->value=02;
        case "March": $data[$i]->month->value=03;
        case "April": $data[$i]->month->value=04;
        case "May": $data[$i]->month->value=05;
        case "June": $data[$i]->month->value=06;
        case "July": $data[$i]->month->value=07;
        case "August": $data[$i]->month->value=08;
        case "September": $data[$i]->month->value=09;
        case "October": $data[$i]->month->value=10;
        case "November": $data[$i]->month->value=11;
        case "December": $data[$i]->month->value=12;
    }
}
var_dump($data);
echo "<hr>";


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
var_dump($dates);
echo "<hr>";

// trends
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
var_dump($trends);
var_dump($keywords);
echo "<hr>";

// write file accordingly
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
var_dump($txt);
$f=fopen("data/timekeywords.csv","w");
fwrite($f,$txt);
fclose($f);

?>
