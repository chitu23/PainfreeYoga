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

// Check if request method is GET
if ($_SERVER["REQUEST_METHOD"] == "GET") {
    try {
        // Prepare the SQL query to fetch all users
        $stmt = $conn->prepare("SELECT name, number, email, age, gender, video, problem FROM signup");
        $stmt->execute();
        $result = $stmt->get_result();

        // Check if any record exists
        if ($result->num_rows > 0) {
            $users = [];
            while ($row = $result->fetch_assoc()) {
                $users[] = $row;
            }
            echo json_encode([
                'status' => true,
                'message' => 'User list retrieved successfully!',
                'data' => $users
            ]);
        } else {
            echo json_encode([
                'status' => false,
                'message' => 'No users found!'
            ]);
        }

        $stmt->close();
    } catch (Exception $e) {
        echo json_encode([
            'status' => false,
            'message' => 'Error retrieving user data: ' . $e->getMessage()
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
