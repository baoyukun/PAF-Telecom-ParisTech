<?php

/*
This scripts aims at creating a JSON file of the coauthors of TPT.
It is based on publis.json:

{
source
publis: [
    {
    (various)2
    author [
        {givenName,familyName}+
    ]
    (various)*
    }
]+
}

and creates a force-oriented-graph-friendly coauthors.json:

{nodes[{id,group}+],links[{source,target,value}+]}

*/

$publis=json_decode(file_get_contents("data/publis.json"))->publis;
$C=new stdClass();
$C->nodes=[];
$C->links=[];

// authors list
$alist=[];
foreach ($publis as $p){
    $authors=$p->author;
    foreach ($authors as $a){
        if (!in_array($a->familyName,$alist)){
            $alist[]=$a->familyName;
        }
    }
}

// collaboration list
$L=[];
foreach ($alist as $a){
    $L[$a]=[];
}
foreach ($publis as $p){
    foreach ($p->author as $a1){
        foreach ($p->author as $a2){
            if (array_key_exists($a2->familyName,$L[$a1->familyName])){
                $L[$a1->familyName][$a2->familyName]++;
            }
            else {
                $L[$a1->familyName][$a2->familyName]=1;
            }
        }
    }
}

// $C->nodes filling
foreach ($alist as $a){
    $C->nodes[]=new stdClass();
    $C->nodes[count($C->nodes)-1]->id=$a;
    $C->nodes[count($C->nodes)-1]->group=1;
}
foreach ($alist as $a1){
    foreach ($alist as $a2){
        if ($a1!=$a2 && array_key_exists($a2,$L[$a1])){
            $C->links[]=new stdClass();
            $C->links[count($C->links)-1]->source=$a1;
            $C->links[count($C->links)-1]->target=$a2;
            $C->links[count($C->links)-1]->value=$L[$a1][$a2];
        }
    }
}

$f=fopen("data/coauteurs.json","w");
fwrite($f,json_encode($C));
fclose($f);

$nblinks=0;
foreach ($L as $a){
    $nblinks+=count($a);
}
$maxlink=0;
foreach ($C->links as $link){
    if ($link->value>$maxlink){
        $maxlink=$link->value;
    }
}
echo "<a href='display.html'>done.</a><br>";
echo "number of authors: ".count($alist)."<br>";
echo "number of links: ".$nblinks."<br>";
echo "maximum number of publications: ".$maxlink."<br>";

?>
