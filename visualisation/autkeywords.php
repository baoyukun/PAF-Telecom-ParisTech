<!-- Link the authors by their common keywords. Creates a FDG-ready-to-display JSON file -->

<?php

include("utils.php");
$gad=json_decode(file_get_contents("data/groupsanddepts.json"));

$data=json_decode(file_get_contents("data/autkeywords.json"))->results->bindings;
for ($i=0; $i<count($data); $i++){
    $data[$i]->familyName->value=ucwords(strtolower($data[$i]->familyName->value));
}

//var_dump($data);echo "<hr>";

$chercheurs=[];
foreach ($data as $d){
    $name=$d->familyName->value;
    if (!in_array($name,$chercheurs)){
        $chercheurs[]=$name;
    }
}
//var_dump($chercheurs);echo "<hr>";


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
//var_dump($keywords);echo "<hr>";

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
//var_dump($co);echo"<hr>";

$j=new stdClass();
$j->nodes=[];
foreach ($chercheurs as $c){
    $j->nodes[]=new stdClass();
    $j->nodes[count($j->nodes)-1]->id=$c;
    $j->nodes[count($j->nodes)-1]->group=getGroup($c,$gad);
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

$f=fopen("data/authorkeywords.json","w");
fwrite($f,json_encode($j));
fclose($f);

?>
