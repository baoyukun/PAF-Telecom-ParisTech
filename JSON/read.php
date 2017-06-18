<?php

// gets the content of the JSON file and stores it as a string
$JSONstr=file_get_contents("data.json");

// converts the JSON string into a PHP object
$PHPobj=json_decode($JSONstr);

/* DO THINGS WITH THE PHP OBJECT HERE */

// for comprehension: signals the end of the process and dumps the variables
echo "done";
var_dump($JSONstr);
var_dump($PHPobj);

?>
