<?php

require "connect.php";

$query="select * from donate";

$result= mysqli_query($conn,$query);

while($row=mysqli_fetch_assoc($result))
{
	$data[]=$row;
}

if ($result) {
    print(json_encode($data));
  } else {
    echo "Failed";
  }



?>