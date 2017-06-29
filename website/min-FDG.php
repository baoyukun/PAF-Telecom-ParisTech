<?php

ini_set("memory_limit","256M");
ini_set('max_execution_time', 300); 

$t=time();

// init
$dataurl=$_GET["dataURL"];
$cut=(int)$_GET["cutBy"];
$data=json_decode(file_get_contents($dataurl));

$t=time()-$t;
echo "input: ".count($data->nodes)." nodes and ".count($data->links)." links<br>";
echo "Data loaded in $t seconds, ".count($data->links)." links<br>";ob_flush();flush();
$t=time();

// change link values
$l=count($data->links);
for ($i=0; $i<$l; $i++){
    $data->links[$i]->value=$data->links[$i]->value-$cut;
    if ($data->links[$i]->value<1){
        unset($data->links[$i]);
    }
}
$data->links=array_values($data->links);

$t=time()-$t;
echo "Values modified in $t seconds, ".count($data->links)." links left<br>";ob_flush();flush();
$t=time();

// creates totallink
$totallink=[];
foreach ($data->nodes as $c){
    $totallinks[$c->id]=0;
}
foreach ($data->links as $l){
    $totallinks[$l->source]=$totallinks[$l->source]+$l->value;
    $totallinks[$l->target]=$totallinks[$l->target]+$l->value;
}

$t=time()-$t;
echo "total links calculated in $t seconds<br>";ob_flush();flush();
$t=time();


// locate weak nodes
$weak=[];
foreach ($data->nodes as $n){
    if ($totallinks[$n->id]==0){
        $weak[]=$n->id;
    }
}

$t=time()-$t;
echo "Weak nodes located in $t seconds (".count($weak)." weak nodes, ".(count($data->nodes)-count($weak))." strong nodes left)<br>";ob_flush();flush();
$t=time();

// delete the weak nodes
$l=count($data->nodes);
for ($i=0; $i<$l; $i++){
    if (in_array($data->nodes[$i]->id,$weak)){
        unset($data->nodes[$i]);
    }
}

$t=time()-$t;
echo "Weak nodes deleted in $t seconds<br>";ob_flush();flush();
$t=time();

// delete the weak links
$l=count($data->links);
for ($i=0; $i<$l; $i++){
    if (in_array($data->links[$i]->source,$weak) || in_array($data->links[$i]->target,$weak)){
        unset($data->links[$i]);
    }
}
$data->links=array_values($data->links);

$t=time()-$t;
echo "Weak links deleted in $t seconds<br>";ob_flush();flush();
$t=time();

// re-index the arrays so that the JSON encoding encodes them well
$data->nodes=array_values($data->nodes);
$data->links=array_values($data->links);

$t=time()-$t;
echo "Arrays reindexed in $t seconds<br>";ob_flush();flush();
$t=time();

// write back
$f=fopen(substr($dataurl,0,-5)."-min".$cut.".json","w");
fwrite($f,json_encode($data));
fclose($f);

$t=time()-$t;
echo "output: ".count($data->nodes)." nodes and ".count($data->links)." links<br>";
echo "Object written back in $t seconds<br><h1>Done</h1>";
$t=time();

?>














