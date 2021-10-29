<?php

require ('connect.php');

// $username = "ali";
// $password = "ali123";
// $manager = 1;
// $j_date = "12-09-2020";


$username = $_POST["un"];
$password = $_POST["ps"];
$phone = $_POST["ph"];
$address = $_POST["ad"];
$email = $_POST["em"];
$type = $_POST["tp"];
$profile_image = $_POST["pr"];

$filename = "IMG".rand().".jpeg";
file_put_contents("profiles/".$filename,base64_decode($profile_image));


// $username = "ali";
// $password = "ali123";
// $phone = 3123;
// $address = "adas";
// $email = "adas";
// $type = "dsf";


$sql = "INSERT INTO register ( uname , upass , uphone , uaddress , uemail , utype, profile_image) VALUES ('$username', '$password', '$phone' , '$address' , '$email' , '$type', '$filename' )";
$result = mysqli_query($conn , $sql) or die ("Regsiter Failed");

if ($result) {
  echo "Registered";
} else {
  echo "Register-Failed";
}
?>