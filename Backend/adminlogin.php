<?php

include_once 'db.php';  // Ensure the database connection is correct

// Set headers for JSON response
header('Content-Type: application/json; charset=utf-8');

// Check the connection
if ($conn->connect_error) {
    echo json_encode([
        'status' => false,
        'message' => 'Connection failed: ' . $conn->connect_error
    ]);
    exit;
}

// Check if data was received via POST
if ($_SERVER["REQUEST_METHOD"] == "POST") {

    // Get the form data
    $email = isset($_POST['email']) ? trim($_POST['email']) : '';
    $password = isset($_POST['password']) ? trim($_POST['password']) : '';

    // Validate the input data
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

    // Check if the email exists in the database
    try {
        $stmt = $conn->prepare("SELECT * FROM adminsignup WHERE email = ?");
        $stmt->bind_param("s", $email);
        $stmt->execute();
        $result = $stmt->get_result();

        // If the email exists, verify the password
        if ($result->num_rows > 0) {
            $adminData = $result->fetch_assoc();

            // Verify the password
            if (password_verify($password, $adminData['password'])) {
                echo json_encode([
                    'status' => true,
                    'message' => 'Login successful!',
                    'data' => [
                        'adminname' => $adminData['adminname'],
                        'email' => $adminData['email'],
                        'adminid' => $adminData['adminid']
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

        $stmt->close();
    } catch (Exception $e) {
        echo json_encode([
            'status' => false,
            'message' => 'Error during login: ' . $e->getMessage()
        ]);
        exit;
    }

    // Close the connection
    $conn->close();

} else {
    echo json_encode([
        'status' => false,
        'message' => 'Invalid request method!'
    ]);
}
?>
