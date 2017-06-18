<?php
$JSONstr=file_get_contents("data.json");
?>

<html>
    <head>
        <script>
            var JSvar = JSON.parse('<?php echo $JSONstr; ?>');
            // actions using this javascript variables such as constructing a graph can be done. Here, the variable is simply logged to the console.
            console.log(JSvar);
        </script>
    </head>
    <body>
        <p>Go to the console to see the JavaScript variable.</p>
    </body>
</html>
