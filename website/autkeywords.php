<?php

$t=time();

include("utils.php");
$gad=json_decode(file_get_contents("data/depts.json"));
$data=json_decode(file_get_contents("data/autkeywords.json"))->results->bindings;
for ($i=0; $i<count($data); $i++){
    $data[$i]->familyName->value=ucwords(strtolower($data[$i]->familyName->value));
}

$t=time()-$t;
echo "init took $t seconds<br>";ob_flush();flush();
$t=time();

$chercheurs=[];
foreach ($data as $d){
    $name=$d->familyName->value;
    if (!in_array($name,$chercheurs)){
        $chercheurs[]=$name;
    }
}

$t=time()-$t;
echo "built chercheurs in $t seconds<br>";ob_flush();flush();
$t=time();

$keywords=[];
foreach ($chercheurs as $c){
    $keywords[$c]=[];
}
foreach ($data as $d){
    $keywords[$d->familyName->value][]=$d->keyword->value;
}
foreach ($chercheurs as $c){
    sort($keywords[$c]);
}

$t=time()-$t;
echo "built keywords in $t seconds<br>";ob_flush();flush();
$t=time();

$co=[];
foreach ($chercheurs as $c1){
    $co[$c1]=[];
    foreach ($chercheurs as $c2){
        if ($c1!=$c2){
            $val=count(array_intersect($keywords[$c1],$keywords[$c2]));
            if ($val>1){
                $co[$c1][$c2]=$val;
            }
        }
    }
}

$t=time()-$t;
echo "built connexions in $t seconds<br>";ob_flush();flush();
$t=time();

$j=new stdClass();
$j->nodes=[];
foreach ($chercheurs as $c){
    $j->nodes[]=new stdClass();
    $j->nodes[count($j->nodes)-1]->id=$c;
    $j->nodes[count($j->nodes)-1]->group=getDept(strtolower($c),$gad);
}
$j->links=[];
foreach ($co as $c1=>$c){
    foreach ($c as $c2=>$value){
        if ($c1!=$c2){
            $j->links[]=new stdClass();
            $j->links[count($j->links)-1]->source=$c1;
            $j->links[count($j->links)-1]->target=$c2;
            $j->links[count($j->links)-1]->value=$value;
        }
    }
}

$t=time()-$t;
echo "built object in $t seconds<br>";ob_flush();flush();
$t=time();

$f=fopen("data/authorkeywords.json","w");
fwrite($f,json_encode($j));
fclose($f);

echo "<h1>done</h1>";

?>