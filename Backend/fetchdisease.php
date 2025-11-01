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
    die("Database connection failed: " . $e->getMessage());
}

// Check if the request is GET
if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    // Retrieve the disease name from query parameters
    $diseasename = $_GET['diseasename'] ?? '';

    // Validate the input
    if (empty($diseasename)) {
        echo json_encode(['status' => 'error', 'message' => 'Disease name is required']);
        exit;
    }

    try {
        // Fetch data from the table
        $sql = "SELECT * FROM disease WHERE diseasename = :diseasename";
        $stmt = $pdo->prepare($sql);
        $stmt->execute([':diseasename' => $diseasename]);
        $data = $stmt->fetchAll(PDO::FETCH_ASSOC);

        if ($data) {
            echo json_encode(['status' => 'success', 'data' => $data]);
        } else {
            echo json_encode(['status' => 'error', 'message' => 'No records found for the given disease name']);
        }
    } catch (PDOException $e) {
        echo json_encode(['status' => 'error', 'message' => 'Error fetching data: ' . $e->getMessage()]);
    }
} else {
    echo json_encode(['status' => 'error', 'message' => 'Invalid request method']);
}
?>
