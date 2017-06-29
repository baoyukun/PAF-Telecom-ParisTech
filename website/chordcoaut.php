<?php

$t=time();
ini_set("memory_limit", "1G");
include("utils.php");
$gad=json_decode(file_get_contents("data/depts.json"));
$data=json_decode(file_get_contents("data/publis-auteurs.json"))->results->bindings;
for ($i=0; $i<count($data); $i++){
    $data[$i]->familyName->value=ucwords(strtolower($data[$i]->familyName->value));
}

$t=time()-$t;
echo "init done in $t seconds<br>";ob_flush();flush();
$t=time();

$deptpublis=[];
foreach ($data as $d){
    $name=$d->familyName->value;
    $dept=getDept(strtolower($name),$gad);
    $title=$d->title->value;
    if (!array_key_exists($dept,$deptpublis)){
        $deptpublis[$dept]=[];
    }
    $deptpublis[$dept][]=$title;
}

$t=time()-$t;
echo "publis built in $t seconds<br>";ob_flush();flush();
$t=time();

$co=[];
foreach ($deptpublis as $dept1=>$publis1){
    $co[$dept1]=[];
    foreach ($deptpublis as $dept2=>$publis2){
        $co[$dept1][$dept2]=count(array_intersect($publis1,$publis2));
    }
}
var_dump($co);

$res="[";
foreach ($co as $co1){
    $res.="[";
    foreach ($co1 as $co2){
        $res.=$co2.",";
    }
    $res.="],";
}
$res.="]";
$res=preg_replace("#,]#","]",$res);

$t=time()-$t;
echo "co built in $t seconds<br>";ob_flush();flush();
$t=time();

$f=fopen("data/chordcoaut.txt","w");
fwrite($f,$res);
fclose($f);

echo "<h1>done</h1>";

?>