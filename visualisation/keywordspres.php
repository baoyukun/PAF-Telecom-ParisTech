<!-- build a json that will presents the keywords as a collapsible tree. A keyword is the father of another keyword if both are used in the same article. Thus, close to the root, are the main concepts, and close to the leaves are the specific concepts. The bigger the leaf, the more frequently the word is used. -->

<?php

$data=json_decode(file_get_contents("data/publiskeywords.json"))->results->bindings;
class keyword {
    public $name;
    public $children;
    public $friends;
    public $freq;
    
    function __construct($name,$freq){
        $this->name=$name;
        $this->children=[];
        $this->friends=[];
        $this->freq=$freq;
    }
    function eatfriends($child){
        //init
        $eaten=[];
        
        //eat friends
        $l=count($this->children);
        for ($i=0; $i<$l; $i++){
            if (in_array($this->children[$i]->name,$child->friends)){
                $child->children[]=$this->children[$i];
                $eaten[]=$this->children[$i]->name;
                unset($this->children[$i]);
            }
        }
        $this->children=array_values($this->children);
        
        //clean friends list
        $noteaten=[];
        foreach ($this->children as $c){
            if (get_class($c)=="keyword" && count($c->children)==0){
                $noteaten[]=$c->name;
            }
        }
        for ($i=0; $i<count($this->children); $i++){
            if (get_class($c)=="keyword"){
                $this->children[$i]->friends=array_intersect($this->children[$i]->friends,$noteaten);
            }
        }
        for ($i=0; $i<count($child->children); $i++){
            $child->children[$i]->friends=array_intersect($child->children[$i]->friends,$eaten);
        }
        
    }
    function best(){
        $max=0;
        foreach ($this->children as $c){
            if (get_class($c)=="keyword" && count($c->friends)>$max && $c->children==[]){
                $best=$c;
                $max=count($c->friends);
            }
        }
        if ($max>0){
            return $best;
        }
    }
    function flatten(){
        for ($i=0; $i<count($this->children); $i++){
            if (count($this->children[$i]->children)==0 && count($this->children[$i]->friends)==0){
                $name=$this->children[$i]->name;
                $size=1;
                $this->children[$i]=new stdClass;
                $this->children[$i]->name=$name;
                $this->children[$i]->size=$this->freq[$name];
            }
        }
    }
    function flat(){
        foreach ($this->children as $c){
            if (get_class($c)=="keyword"){
                return false;
            }
        }
        return true;
    }
    function build(){
        $this->flatten();
        if ($this->flat()){
            return;
        }
        while ($this->best()!=null){
            $best=$this->best();
            $this->eatfriends($best);
            $best->build();
        }
    }
}

$keywords=[];
$freq=[];
foreach ($data as $d){
    $k=$d->keyword->value;
    if (!in_array($k,$keywords)){
        $keywords[]=$k;
    }
    if (!array_key_exists($k,$freq)){
        $freq[$k]=1;
    }
    else {
        $freq[$k]++;
    }
}
$art=[];
foreach ($data as $d){
    $title=$d->title->value;
    $k=$d->keyword->value;
    if (key_exists($title,$art)){
        $art[$title][]=$k;
    }
    else {
        $art[$title]=[$k];
    }
}
$friends=[];
foreach ($keywords as $k){
    $friends[$k]=[];
}
foreach ($art as $a=>$kws){
    foreach ($kws as $k1){
        foreach ($kws as $k2){
            if ($k1!=$k2 && !in_array($k2,$friends[$k1])){
                $friends[$k1][]=$k2;
            }
        }
    }
}
echo "<h1>keywords</h1>";var_dump($keywords);
echo "<h1>freq</h1>";var_dump($freq);
echo "<h1>art</h1>";var_dump($art);
echo "<h1>friends</h1>";var_dump($friends);
echo "<hr>";


$root=new keyword("root",$freq);
$root->friends=$keywords;
$kws=[];
foreach ($keywords as $k){
    eval("$$k=new keyword('$k',\$freq);");
    eval("\$kws[]=$$k;");
}
foreach ($kws as $kw){
    $kw->friends=$friends[$kw->name];
    $root->children[]=$kw;
}
echo "<h1>root</h1>";var_dump($root);
echo "<hr>";

$root->build();
$root->friends=[];
$j=json_encode($root);
$j=preg_replace('#,"friends":\[\]#',"",$j);
$j=preg_replace('#,"freq":{[\"a-zA-Z:0-9, ]*}#',"",$j);
var_dump($j);

$f=fopen("data/keywords.json","w");
fwrite($f,$j);
fclose($f);

?>
