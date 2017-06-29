<?php

ini_set("memory_limit","256M");
$t=time();

$dataurl=$_GET["dataURL"];
$data=json_decode(file_get_contents($dataurl));
$gad=json_decode(file_get_contents("data/groupsanddepts.json"));
include("utils.php");

$t=time()-$t;
echo "loaded ".count($data->nodes)." nodes and ".count($data->links)." links in $t seconds<br>";ob_flush();flush();
$t=time();


$l=count($data->nodes);
for ($i=0; $i<$l; $i++){
    if ($data->nodes[$i]->group==0){
        unset($data->nodes[$i]);
    }
}
$data->nodes=array_values($data->nodes);

$t=time()-$t;
echo "deleted names in $t seconds, ".(count($data->nodes))." names left<br>";ob_flush();flush();
$t=time();

$l=count($data->links);
for ($i=0; $i<$l; $i++){
    if (getDept($data->links[$i]->source,$gad)==0 || getDept($data->links[$i]->target,$gad)==0){
        unset($data->links[$i]);
    }
}
$data->links=array_values($data->links);

$t=time()-$t;
echo "deleted the corresponding links in $t seconds<br>";ob_flush();flush();
$t=time();

$f=fopen(substr($dataurl,0,-5)."-out.json","w");
fwrite($f,json_encode($data));
fclose($f);

?>