<?php

$t=time();

ini_set("memory_limit", "512M");
$publisdept=json_decode(file_get_contents("data/publis-dept.json"))->results->bindings;
$publisauth=json_decode(file_get_contents("data/publis-auteurs.json"))->results->bindings;

$t=time()-$t;
echo "init done in $t seconds<br>";ob_flush();flush();
$t=time();

$pdept=[];
foreach ($publisdept as $p){
    $pdept[$p->title->value]=$p->departement->value;
}

$t=time()-$t;
echo "pdept built in $t seconds<br>";ob_flush();flush();
$t=time();

$autdept=[];
foreach ($publisauth as $p){
    $name=$p->familyName->value;
    $title=$p->title->value;
    if (array_key_exists($title,$pdept)){
        $dept=$pdept[$title];
        if (!key_exists($name,$autdept)){
            $autdept[$name]=[];
        }
        if (!key_exists($dept,$autdept[$name])){
            $autdept[$name][$dept]=1;
        }
        else {
            $autdept[$name][$dept]++;
        }
    }
}

$t=time()-$t;
echo "autdept built in $t seconds<br>";ob_flush();flush();
$t=time();

$trueautdept=[];
foreach ($autdept as $aut=>$deptlist){
    $d=array_search(max($deptlist),$deptlist);
    switch ($d){
        case "egsh": $trueautdept[$aut]=1; break;
        case "tsi": $trueautdept[$aut]=2; break;
        case "comelec": $trueautdept[$aut]=3; break;
        case "infres": $trueautdept[$aut]=4; break;
        default: $trueautdept[$aut]=0; break;
    }
}

$t=time()-$t;
echo "trueautdept built in $t seconds<br>";ob_flush();flush();
$t=time();

$j=new stdClass();
foreach ($trueautdept as $aut=>$dept){
    $j->$aut=new stdClass();
    $j->$aut->dept=$dept;
}

$f=fopen("data/depts.json","w");
fwrite($f,json_encode($j));
fclose($f);

echo "<h3>done</h3>";

var_dump($j);

?>
