<?php

$name=$_GET["name"];
$message=$_GET["message"];

$f=fopen("contact.txt","a+");
$txt="at ".time().", ".$name." said:\r\n\r\n".message."\r\n\r\n\r\n";
fwrite($f,$txt);
fclose($f);

?>