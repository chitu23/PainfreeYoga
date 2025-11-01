<?php
// Database connection
$host = 'localhost'; // Database host
$db = 'painfreeyoga'; // Database name
$user = 'root'; // Database username
$password = ''; // Database password

header('Content-Type: application/json'); // Set header for JSON response

try {
    // Create a PDO instance
    $pdo = new PDO("mysql:host=$host;dbname=$db", $user, $password);
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);   
} catch (PDOException $e) {
    echo json_encode(['status' => 'error', 'message' => 'Database connection failed: ' . $e->getMessage()]);
    exit;
}

// Check if the request method is GET
if ($_SERVER['REQUEST_METHOD'] === 'GET') {
    try {
        $sql = "SELECT * FROM disease"; // Query to fetch all data
        $stmt = $pdo->prepare($sql);
        $stmt->execute();

        // Fetch data as an associative array
        $results = $stmt->fetchAll(PDO::FETCH_ASSOC);

        // Check if data exists
        if (!empty($results)) {
            echo json_encode(['status' => 'success', 'data' => $results], JSON_PRETTY_PRINT);
        } else {
            echo json_encode(['status' => 'success', 'message' => 'No data found']);
        }
    } catch (PDOException $e) {
        echo json_encode(['status' => 'error', 'message' => 'Error retrieving data: ' . $e->getMessage()]);
    }
} else {
    echo json_encode(['status' => 'error', 'message' => 'Invalid request method. Use GET.']);
}
?>
