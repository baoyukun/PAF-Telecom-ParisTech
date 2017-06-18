<?php

// example of a php object to be written as a JSON file
$PHPobj=["name"=>"John Doe", "age"=>39, "family"=>["mom"=>"Jane Doe","wife"=>"Sarah Connor"]];

// convert into the string of a JSON object
$JSONobj=json_encode($PHPobj);

// writes on file
$file=fopen("data.json","w");
fwrite($file,$JSONobj);
fclose($file);

// signals the end of the php process and displays the variables
echo "done";
var_dump($PHPobj);
var_dump($JSONobj);

?>
