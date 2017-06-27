<!--

This script takes as an input a .json file formatted to fit a force-directed graph:
  {nodes:[{id,group},...,{id,group}], links:[{source,target,value},...,{source,target,value}]}
and cuts out the "weak links", ie the links with a value inferior to the input "cut". Nodes with no connexions are deleted afterward, and then the filtered .json file is written somewhere with a similar name. ex:

min-DFG?dataURL=data/coauteurs.json&cut=10
^^^^
writes data/coauteurs-min10.json where only people with 10+ common publications appear

-->

<?php
$t=time();

//utils
function totalLinks($name,$data){
    $tot=0;
    foreach ($data->links as $l){
        if ($l->source==$name || $l->target==$name){
            $tot+=$l->value;
        }
    }
    return $tot;
}

// init
$dataurl=$_GET["dataURL"];
$cut=$_GET["cutBy"];
$data=json_decode(file_get_contents($dataurl));

// change link values
for ($i=0; $i<count($data->links); $i++){
    $data->links[$i]->value=max(0,$data->links[$i]->value-$cut);
}

// locate weak nodes
$weak=[];
foreach ($data->nodes as $n){
    if (totalLinks($n->id,$data)==0){
        $weak[]=$n->id;
    }
}

// delete the weak nodes
$l=count($data->nodes);
for ($i=0; $i<$l; $i++){
    if (in_array($data->nodes[$i]->id,$weak)){
        unset($data->nodes[$i]);
    }
}

// delete the weak links
$l=count($data->links);
for ($i=0; $i<$l; $i++){
    if (in_array($data->links[$i]->source,$weak)||in_array($data->links[$i]->target,$weak)){
        unset($data->links[$i]);
    }
}

// re-index the arrays so that the JSON encoding encodes them well
$data->nodes=array_values($data->nodes);
$data->links=array_values($data->links);

// write back
$f=fopen(substr($dataurl,0,-5)."-min".$cut.".json","w");
fwrite($f,json_encode($data));
fclose($f);

// report
$t=time()-$t;
echo "done ($t seconds)<hr><h1>Data:</h1>";
var_dump($data);
echo "<h1>Nodes:</h1>";
foreach ($data->nodes as $n){
    var_dump([$n->id,totalLinks($n->id,$data)]);
}
?>
