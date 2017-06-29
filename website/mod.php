<?php

ini_set("memory_limit", "512M");

$j=json_decode(file_get_contents("data/coauteurs.json"));
$fails=0;
foreach ($j->nodes as $n){
    if ($n->group==0){
        $fails++;
    }
}
var_dump($fails/count($j->nodes));

?>