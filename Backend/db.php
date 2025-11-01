<?php

$servername = "localhost";  // or your database server address
$username = "root";         // your MySQL username
$password = "";             // your MySQL password
$dbname = "painfreeyoga";   // your database name

// Enable error reporting for debugging
error_reporting(E_ALL);
ini_set('display_errors', 1);

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);

// Check the connection
// if ($conn->connect_error) {
//     // If connection fails, output the error message
//     echo "Connection failed: " . $conn->connect_error;
//     exit; // Stop the script
// } else {
//     // If connection is successful, output success message
//     echo "Connection successful to the database!";
// }

// Optionally, output the connection object for debugging
// var_dump($conn);
?>
