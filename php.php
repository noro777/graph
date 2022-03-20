<?php


// C matric
// $C_matric[] = array();
// $i = 0;
// while(true){
//     $C = explode(',', readline("var1,var2\n"));
//     if($C[0])
//     list($C_matric[$i][0], $C_matric[$i][1]) = $C;
//     else break;
//     $i++;
// }

$C_matric = [[1,3],
            [2,3],
            [2,4],
            [3,4],
            [3,5],
            [4,5],
            [4,7],
            [5,6],
            [6,7],
            [6,0],
            [7,0]];
// print_r($C_matric);

// T1 T2 T3

$t1 = array();
$t2 = array();
$t3 = array();

foreach($C_matric as $c){
    $bool = true;
    if($c[1] == 0 && !in_array($c[0],$t3)){
        array_push($t3,$c[0]);
        continue;
    } 
    foreach($C_matric as $m){
        if($c[0] == $m[1]){
            $bool = false;
            !in_array($c[0],$t2) ? array_push($t2,$c[0]) : "";
        }
    }
    if($bool && !in_array($c[0],$t1)) array_push($t1,$c[0]);       
}

echo "T1 ";
foreach($t1 as $value)echo " ".$value;
echo "\n";
echo "T2 ";
foreach($t2 as $value)echo " ".$value;
echo "\n";
echo "T3 ";
foreach($t3 as $value)echo " ".$value;
echo "\n";

//T5
$t5 = 0;
foreach($t2 as $i)
    foreach($C_matric as $j)
        if($i == $j[0] && in_array($j[1], $t2)) $t5++;

echo "T5 ".$t5;
echo "\n";
//T6
$t6 = 0;
foreach($t3 as $i)
    foreach($C_matric as $j)
        if($i == $j[0] && in_array($j[1], $t3))$t6++;

echo "T6 ".$t6;
echo "\n";
//A1

$max = max($C_matric)[0];

$A[][] = array();
$A1[] = array();
$i = 0;
$j = 0;
while($i < $max){
    while($j < $max){
        $A1[$i][$j] = 0;
        $j++;
    }
    $j = 0;
    $i++;
}
foreach($C_matric as $c){
    if($c[1]){
        $A1[$c[0]-1][$c[1]-1] = 1;
    }
}

$i = 0;
$j = 0;
$bool1 = false;
echo "A1 \n";
while($i < $max){
    while($j < $max){
        echo $A1[$i][$j];
        if($i == $j && $A1[$i][$j] != 0) $bool1 = true;
        $j++;
    }
    echo "\n";
    $j = 0;
    $i++;
}
if($bool1) echo "erroorr";

//A...

$Ai = 2;
$A2 = $A1;
$A[0] = $A1;
while(true){
    $i = 0;
    $arr3 = Ai($A1,$A2,$max);
    echo "A".$Ai."\n";
    foreach($arr3 as $values){
        foreach($values as $value){
            if($value != 0)$i++;
            echo $value;
        }
        echo "\n";
    }
    if($i){
        $A[$Ai-1] = $arr3;
        $Ai++;
        $A1 = $A2;
        $A2 = $arr3;
        continue;
    }
    $A[$Ai] = $arr3;
    break;
}

//T4 T7
$t4 = array();
$t7 = array();
for($i=0; $i<count($A); $i++)
{
    $t4[$i] = 0;
    $t7[$i] = 0;
}

$l = 0;
foreach($A as $A1){
    $i = 0;
    while($i < $max){
        $j = 0;
        $k = 0;
        while($j < $max){
            if($A1[$j][$i] != 0)
            break;
            else $k++;
            $j++;
        }
        if($k == $max){
            $t4[$l]++;
            $t7[$l] = $t4[$l] - $l -1;
        }
        $i++;
    }
    $l++;
}

print_r($t4);
print_r($t7);

function Ai($arr1,$arr2,$max){
    $arr3[] = array();
    $i = 0;
    $j = 0;
    while($i < $max){
        while($j < $max){
            $arr3[$i][$j] = 0;
            $j++;
        }
        $j = 0;
        $i++;
    }
    for($i=0; $i<$max; $i++)
    {
        for($j=0; $j<$max; $j++)
        {
            for($k=0; $k<$max; $k++)
            $arr3[$i][$j] = $arr3[$i][$j]  + $arr1[$i][$k] * $arr2[$k][$j];
        }
    }
    return $arr3;
}

?>