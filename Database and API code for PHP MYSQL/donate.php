<?php

require "connect.php";


//  Params comming from android studio
$item_name = $_POST["it"];
$quantity = $_POST["qt"];
$description = $_POST["ds"];
$category = $_POST["ct"];
$r_id = $_POST["rid"];
$type = $_POST["type"];
$im = $_POST["im"];

$filename = "IMG".rand().".jpeg";
file_put_contents("images/".$filename,base64_decode($im));


//  Insert data for donation in database
$sql = "INSERT INTO donate (item_name, category,quantity, note, r_id , item_image , type)
VALUES ('$item_name', '$category' , '$quantity', '$description', '$r_id', '$filename'  , '$type' )";
$result = mysqli_query($conn , $sql) or die ("Regsiter Failed");


//  Showing result values
if ($result) {
  echo "Registered";
} else {
  echo "Failed";
}



// $filename = $_FILES['image']['name'];
// $filesize = $_FILES['image']['size'];
// $filetmp = $_FILES['image']['tmp_name'];
// $des = "images/".$im;

// move_uploaded_file($im , $des);


//  Test Data

// $item_name = '$_POST["it"];';
// $quantity = '2';
// $note = '$_POST["ds"];';
// $item_image = '$_POST["im"];';
// $r_id = '1';

// $on = $_FILES["im"]["name"];
// $tn = $_FILES["im"]["tmp_name"];
// $dn = "images/".$on;
// move_uploaded_file($tn,$dn);

?>