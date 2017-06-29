<?php

ini_set("memory_limit", "1G");

$t=time();
include("utils.php");
$gad=json_decode(file_get_contents("data/depts.json"));
$data=json_decode(file_get_contents("data/publis-auteurs.json"))->results->bindings;
for ($i=0; $i<count($data); $i++){
    $data[$i]->familyName->value=ucwords(strtolower($data[$i]->familyName->value));
}

$t=time()-$t;
echo "<h3>Data loaded, time of calculation: $t seconds</h3>";ob_flush();flush();
$t=time();

$publis=[];
foreach ($data as $d){
    $title=$d->title->value;
    $name=$d->familyName->value;
    if (!array_key_exists($title,$publis)){
        $publis[$title]=[];
    }
    $publis[$title][]=$name;
}

$t=time()-$t;
echo "<h3>Publications loaded, time of calculation: $t seconds</h3>";ob_flush();flush();
$t=time();

$chercheurs=[];
foreach ($data as $d){
    $name=$d->familyName->value;
    if (!in_array($name,$chercheurs)){
        $chercheurs[]=$name;
    }
}

$t=time()-$t;
echo "<h3>Scientists list built, time of calculation: $t seconds</h3>";ob_flush();flush();
$t=time();

$co=[];
foreach ($chercheurs as $c){
    $co[$c]=[];
}
foreach ($publis as $p){
    foreach ($p as $c1){
        foreach ($p as $c2){
            if (array_key_exists($c2,$co[$c1])){
                $co[$c1][$c2]++;
            }
            else {
                $co[$c1][$c2]=1;
            }
        }
    }
}

$t=time()-$t;
echo "<h3>Connexion list built, time of calculation: $t seconds</h3>";ob_flush();flush();
$t=time();

$jobj=new stdClass();
$jobj->nodes=[];
foreach ($chercheurs as $c){
    $jobj->nodes[]=new stdClass();
    $jobj->nodes[count($jobj->nodes)-1]->id=$c;
    $jobj->nodes[count($jobj->nodes)-1]->group=getDept(strtolower($c),$gad));
}
$jobj->links=[];
foreach ($co as $c1=>$c){
    foreach ($c as $c2=>$value){
        if ($c1!=$c2){
            $jobj->links[]=new stdClass();
            $jobj->links[count($jobj->links)-1]->source=$c1;
            $jobj->links[count($jobj->links)-1]->target=$c2;
            $jobj->links[count($jobj->links)-1]->value=$value;
        }
    }
}

$t=time()-$t;
echo "<h3>Json object built, time of calculation: $t seconds</h3>";ob_flush();flush();
$t=time();

$f=fopen("data/coauteurs.json","w");
fwrite($f,json_encode($jobj));
fclose($f);

$t=time()-$t;
echo "<h3>Json object written, time of calculation: $t seconds</h3>";ob_flush();flush();
$t=time();

?>
