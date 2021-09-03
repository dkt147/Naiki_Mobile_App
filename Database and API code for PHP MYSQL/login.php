<?php

require "connect.php";


$username = $_POST["un"];
$password = $_POST["ps"];

$sql = "select * from register where uphone= '$username' and upass = '$password'";
$result = mysqli_query($conn , $sql) or die ("Regsiter Failed");
      
      if(mysqli_num_rows($result) > 0)
      {
          
          $row=mysqli_fetch_assoc($result);
	      print(json_encode($row));

        
          
      }
      else {
         
          echo 'Login-Failed';
      }

?>