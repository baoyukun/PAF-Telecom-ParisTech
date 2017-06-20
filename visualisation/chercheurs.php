<?php

$C=json_decode(file_get_contents("data/chercheurstpt.json"));

$Cmod=new stdClass();
$Cmod->name="Telecom ParisTech";
$Cmod->children=[];
for ($i=0; $i<count($C); $i++){
    $Cmod->children[$i]=new stdClass();
    $Cmod->children[$i]->name=$C[$i]->name;
    $Cmod->children[$i]->children=[];
    for ($j=0; $j<count($C[$i]->groups); $j++){
        $Cmod->children[$i]->children[$j]=new stdClass();
        $Cmod->children[$i]->children[$j]->name=$C[$i]->groups[$j]->name;
        $Cmod->children[$i]->children[$j]->children=[];
        for ($k=0; $k<count($C[$i]->groups[$j]->people); $k++){
            $Cmod->children[$i]->children[$j]->children[$k]=new stdClass();
            $Cmod->children[$i]->children[$j]->children[$k]->name=$C[$i]->groups[$j]->people[$k]->givenName." ".$C[$i]->groups[$j]->people[$k]->familyName;
            $Cmod->children[$i]->children[$j]->children[$k]->size=1;
        }
    }
}

$Cmodjson=json_encode($Cmod);

$f=fopen("data/chercheurstptd3.json","w");
fwrite($f,$Cmodjson);
fclose($f);

?>
