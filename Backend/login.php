<?php
include_once 'db.php';  // Ensure database connection is established

// Set headers for JSON response
header('Content-Type: application/json; charset=utf-8');

// Check database connection
if (!$conn) {
    echo json_encode([
        'status' => false,
        'message' => 'Database connection failed: ' . mysqli_connect_error()
    ]);
    exit;
}

// Ensure the request method is POST
if ($_SERVER["REQUEST_METHOD"] !== "POST") {
    echo json_encode([
        'status' => false,
        'message' => 'Invalid request method!'
    ]);
    exit;
}

// Get and sanitize form data
$email = isset($_POST['email']) ? trim($_POST['email']) : '';
$password = isset($_POST['password']) ? trim($_POST['password']) : '';

// Validate required fields
if (empty($email) || empty($password)) {
    echo json_encode([
        'status' => false,
        'message' => 'Email and Password are required!'
    ]);
    exit;
}

// Validate email format
if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
    echo json_encode([
        'status' => false,
        'message' => 'Invalid email format!'
    ]);
    exit;
}

// Prepare the SQL statement
$query = "SELECT name, email, number, video, password FROM signup WHERE email = ?";
$stmt = $conn->prepare($query);

if (!$stmt) {
    echo json_encode([
        'status' => false,
        'message' => 'Database error: ' . $conn->error // Display SQL error for debugging
    ]);
    exit;
}

$stmt->bind_param("s", $email);
$stmt->execute();
$result = $stmt->get_result();

// Process login
if ($result->num_rows > 0) {
    $userData = $result->fetch_assoc();

    // Verify the password
    if (password_verify($password, $userData['password'])) {
        echo json_encode([
            'status' => true,
            'message' => 'Login successful!',
            'data' => [
                'name' => $userData['name'],
                'email' => $userData['email'],
                'number' => $userData['number'],
                'video' => $userData['video']
            ]
        ]);
    } else {
        echo json_encode([
            'status' => false,
            'message' => 'Incorrect password!'
        ]);
    }
} else {
    echo json_encode([
        'status' => false,
        'message' => 'Email not found!'
    ]);
}

// Clean up
$stmt->close();
$conn->close();

?>
