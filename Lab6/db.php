<?php
    require_once 'vendor/autoload.php';
    $servername='localhost';
    $username='advai';
    $password='password';
    $dbname = "my_db";
    $conn=mysqli_connect($servername,$username,$password,"$dbname");
    if(!$conn){
      die('Could not Connect MySql Server:' .mysql_error());
    }
?>