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

// Check if the request method is GET
if ($_SERVER["REQUEST_METHOD"] == "GET") {

    try {
        // SQL query to fetch all questions and emails
        $query = "SELECT email, question FROM question";
        $result = $conn->query($query);

        if ($result === false) {
            echo json_encode([
                'status' => false,
                'message' => 'Error executing query: ' . $conn->error
            ]);
            exit;
        }

        // Check if there are results
        if ($result->num_rows > 0) {
            $questions = [];

            // Fetch each row as an associative array
            while ($row = $result->fetch_assoc()) {
                $questions[] = $row;
            }

            echo json_encode([
                'status' => true,
                'data' => $questions
            ]);
        } else {
            echo json_encode([
                'status' => true,
                'data' => [],
                'message' => 'No questions found!'
            ]);
        }

        // Free the result set
        $result->free();
    } catch (Exception $e) {
        echo json_encode([
            'status' => false,
            'message' => 'Error fetching data: ' . $e->getMessage()
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
