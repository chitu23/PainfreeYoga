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
    echo "Database connection successful!";
} catch (PDOException $e) {
    die("Database connection failed: " . $e->getMessage());
}

// Check if the request is POST
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    // Retrieve form data
    $adminname = $_POST['adminname'] ?? '';
    $age = $_POST['age'] ?? '';
    $gender = $_POST['gender'] ?? '';
    $mobilenumber = $_POST['mobilenumber'] ?? '';

    // Validate required fields
    if (empty($adminname) || empty($age) || empty($gender) || empty($mobilenumber)) {
        echo json_encode(['status' => 'error', 'message' => 'All fields are required']);
        exit;
    }

    try {
        // Insert data into the table
        $sql = "INSERT INTO admindetail (adminname, age, gender, mobilenumber) 
                VALUES (:adminname, :age, :gender, :mobilenumber)";
        $stmt = $pdo->prepare($sql);
        $stmt->execute([
            ':adminname' => $adminname,
            ':age' => $age,
            ':gender' => $gender,
            ':mobilenumber' => $mobilenumber
        ]);

        echo json_encode(['status' => 'success', 'message' => 'Admin details saved successfully']);
    } catch (PDOException $e) {
        echo json_encode(['status' => 'error', 'message' => 'Error inserting data: ' . $e->getMessage()]);
    }
} else {
    echo json_encode(['status' => 'error', 'message' => 'Invalid request method']);
}
?>
