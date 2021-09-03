<?php

require "connect.php";




$rid = $_POST['rid'];
$query="select * from register where r_id = '$rid'";

$result= mysqli_query($conn,$query);

while($row=mysqli_fetch_assoc($result))
{
	$data[]=$row;
}

print(json_encode($data));


?>