<?php

require "connect.php";


//  Params comming from android studio
$item_name = $_POST["it"];
$quantity = $_POST["qt"];
$description = $_POST["ds"];
$image = $_POST["im"];
$category = $_POST["ct"];
$r_id = $_POST["rid"];


//  Test Data

// $item_name = '$_POST["it"];';
// $quantity = '2';
// $note = '$_POST["ds"];';
// $item_image = '$_POST["im"];';
// $r_id = '$_POST["rid"];';


//  Insert data for donation in database
$sql = "INSERT INTO donate (item_name, category,quantity, note,  item_image, r_id )
VALUES ('$item_name', '$category' , '$quantity', '$note', '$item_image' , '$r_id' )";
$result = mysqli_query($conn , $sql) or die ("Regsiter Failed");


//  Showing result values
if ($result) {
  echo "Registered";
} else {
  echo "Failed";
}


?>