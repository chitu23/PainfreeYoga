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
    $video = isset($_POST['video']) ? trim($_POST['video']) : '';

    // Validate the input data
    if (empty($email) || empty($video)) {
        echo json_encode([
            'status' => false,
            'message' => 'Email and video are required!'
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

    // Update video field for the given email
    try {
        $stmt = $conn->prepare("UPDATE signup SET video = ? WHERE email = ?");
        $stmt->bind_param("ss", $video, $email);
        
        if ($stmt->execute()) {
            if ($stmt->affected_rows > 0) {
                echo json_encode([
                    'status' => true,
                    'message' => 'Video updated successfully!'
                ]);
            } else {
                echo json_encode([
                    'status' => false,
                    'message' => 'No record found for the given email!'
                ]);
            }
        } else {
            echo json_encode([
                'status' => false,
                'message' => 'Error executing query: ' . $stmt->error
            ]);
        }

        $stmt->close();
    } catch (Exception $e) {
        echo json_encode([
            'status' => false,
            'message' => 'Error updating video: ' . $e->getMessage()
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
