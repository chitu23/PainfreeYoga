<?php
// Database connection
$host = 'localhost'; // Database host
$db = 'painfreeyoga'; // Database name
$user = 'root'; // Database username
$password = ''; // Database password

try {
    // Create a PDO instance
    $pdo = new PDO("mysql:host=$host;dbname=$db", $user, $password);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch (PDOException $e) {
    die(json_encode(['status' => 'error', 'message' => 'Database connection failed: ' . $e->getMessage()]));
}

try {
    // Fetch all data from the 'disease' table
    $sql = "SELECT id, diseasename, yogaimage, yoganame FROM disease";
    $stmt = $pdo->prepare($sql);
    $stmt->execute();
    $result = $stmt->fetchAll(PDO::FETCH_ASSOC);

    // Return the data in JSON format
    echo json_encode(['status' => 'success', 'data' => $result]);
} catch (PDOException $e) {
    echo json_encode(['status' => 'error', 'message' => 'Error fetching data: ' . $e->getMessage()]);
}
?>
