<?php

/* groupsanddept.json creation
$data=json_decode(file_get_contents("data/chercheurstpt.json"));

$groupids=[];$groupnb=1;
$deptids=[];$deptnb=1;
foreach ($data as $dept){
    $deptids[$dept->name]=$deptnb;
    $deptnb++;
    foreach ($dept->groups as $group){
        $groupids[$group->name]=$groupnb;
        $groupnb++;
    }
}

$chercheurs=[];
foreach ($data as $dept){
    foreach ($dept->groups as $group){
        foreach ($group->people as $p){
            $chercheurs[ucwords(strtolower($p->familyName))]=["group"=>$groupids[$group->name],"dept"=>$deptids[$dept->name]];
        }
    }
}

$j=json_encode($chercheurs);
$f=fopen("data/groupsanddepts.json","w");
fwrite($f,$j);
fclose($f);*/

function getGroup($name,$gad){
    if (property_exists($gad,$name)){
        return $gad->$name->group;
    }
    else {
        return 0;
    }
}
function getDept($name,$gad){
    if (property_exists($gad,$name)){
        return $gad->$name->dept;
    }
    else {
        return 0;
    }
}

?>
