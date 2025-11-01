<?php
header("Content-Type: application/json");

// Database connection
$conn = new mysqli("localhost", "root", "", "painfreeyoga");
if ($conn->connect_error) {
    die(json_encode(["error" => "Database connection failed: " . $conn->connect_error]));
}

// Fetch data from the signup table
$query = "SELECT name, number, email, password FROM signup";
$result = $conn->query($query);

if (!$result) {
    echo json_encode(["error" => "Query failed: " . $conn->error]);
    exit;
}

// Build the response data
$signupData = [];


while ($row = $result->fetch_assoc()) {
    $signupData[] = [
        "name" => $row["name"],
        "number" => $row["number"],
        "email" => $row["email"],
        "password" => $row["password"]
    ];
}

// Output the JSON data
echo json_encode($signupData);

$conn->close();
?>
