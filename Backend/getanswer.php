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

    // Get the email from the POST request
    $email = isset($_POST['email']) ? trim($_POST['email']) : '';

    // Validate the email
    if (empty($email)) {
        echo json_encode([
            'status' => false,
            'message' => 'Email is required!'
        ]);
        exit;
    }

    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        echo json_encode([
            'status' => false,
            'message' => 'Invalid email format!'
        ]);
        exit;
    }

    // Prepare SQL to fetch the answer by email
    try {
        $stmt = $conn->prepare("SELECT answer FROM answer WHERE email = ?");

        // Check if the statement was prepared successfully
        if ($stmt === false) {
            echo json_encode([
                'status' => false,
                'message' => 'Error preparing the statement: ' . $conn->error
            ]);
            exit;
        }

        // Bind the parameter
        $stmt->bind_param("s", $email);

        // Execute the query
        $stmt->execute();

        // Fetch the result
        $result = $stmt->get_result();

        // Check if an answer exists for the given email
        if ($result->num_rows > 0) {
            $row = $result->fetch_assoc();
            echo json_encode([
                'status' => true,
                'message' => 'Answer retrieved successfully!',
                'data' => $row
            ]);
        } else {
            echo json_encode([
                'status' => false,
                'message' => 'No answer found for the provided email!'
            ]);
        }

        // Close the statement
        $stmt->close();

    } catch (Exception $e) {
        echo json_encode([
            'status' => false,
            'message' => 'Error during retrieval: ' . $e->getMessage()
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
