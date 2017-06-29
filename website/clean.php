<?php

$txt=file_get_contents("data/chercheurstpt.json");

var_dump(strlen($txt));

$txt=preg_replace("#Ã©#","é",$txt);
$txt=preg_replace("#Ã´#","ô",$txt);
$txt=preg_replace("#Ã«#","ë",$txt);
$txt=preg_replace("#Ã§#","ç",$txt);
$txt=preg_replace("#Ã‰#","É",$txt);
$txt=preg_replace("#Ã¯#","ï",$txt);
$txt=preg_replace("#Ã®#","î",$txt);

var_dump(strlen($txt));

$f=fopen("data/chercheurstpt.json","w");
fwrite($f,$txt);
fclose($f);

?>