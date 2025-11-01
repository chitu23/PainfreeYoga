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
    $answer = isset($_POST['answer']) ? trim($_POST['answer']) : '';

    // Validate the input data
    if (empty($email) || empty($answer)) {
        echo json_encode([
            'status' => false,
            'message' => 'Email and Answer are required!'
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

    // Prepare SQL to insert or update the data
    try {
        // Use INSERT ... ON DUPLICATE KEY UPDATE for upsert
        $stmt = $conn->prepare("
            INSERT INTO answer (email, answer) VALUES (?, ?)
            ON DUPLICATE KEY UPDATE answer = VALUES(answer)
        ");

        // Check if the statement was prepared successfully
        if ($stmt === false) {
            echo json_encode([
                'status' => false,
                'message' => 'Error preparing the statement: ' . $conn->error
            ]);
            exit;
        }

        // Bind the parameters
        $stmt->bind_param("ss", $email, $answer);

        // Execute the query
        if ($stmt->execute()) {
            echo json_encode([
                'status' => true,
                'message' => 'Answer submitted successfully!'
            ]);
        } else {
            echo json_encode([
                'status' => false,
                'message' => 'Error executing query: ' . $stmt->error
            ]);
        }

        // Close the statement
        $stmt->close();
    } catch (Exception $e) {
        echo json_encode([
            'status' => false,
            'message' => 'Error during answer submission: ' . $e->getMessage()
        ]);
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
