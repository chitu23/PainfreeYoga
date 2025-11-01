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
    $name = isset($_POST['name']) ? trim($_POST['name']) : '';
    $number = isset($_POST['number']) ? trim($_POST['number']) : '';
    $email = isset($_POST['email']) ? trim($_POST['email']) : '';
    $password = isset($_POST['password']) ? trim($_POST['password']) : '';
    $age = isset($_POST['age']) ? intval(trim($_POST['age'])) : null;
    $gender = isset($_POST['gender']) ? trim($_POST['gender']) : '';
    $problem = isset($_POST['problem']) ? trim($_POST['problem']) : '';

    // Validate the input data
    if (empty($name) || empty($number) || empty($email) || empty($password) || empty($age) || empty($gender) || empty($problem)) {
        echo json_encode([
            'status' => false,
            'message' => 'All fields are required!'
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

    // Check if the email already exists in the database
    try {
        $emailCheck = $conn->prepare("SELECT * FROM signup WHERE email = ?");
        $emailCheck->bind_param("s", $email);
        $emailCheck->execute();
        $emailResult = $emailCheck->get_result();
        if ($emailResult->num_rows > 0) {
            echo json_encode([
                'status' => false,
                'message' => 'Email is already registered!'
            ]);
            $emailCheck->close();
            exit;
        }
        $emailCheck->close();
    } catch (Exception $e) {
        echo json_encode([
            'status' => false,
            'message' => 'Error checking email: ' . $e->getMessage()
        ]);
        exit;
    }

    // Check if the phone number already exists in the database
    try {
        $numberCheck = $conn->prepare("SELECT * FROM signup WHERE number = ?");
        $numberCheck->bind_param("s", $number);
        $numberCheck->execute();
        $numberResult = $numberCheck->get_result();
        if ($numberResult->num_rows > 0) {
            echo json_encode([
                'status' => false,
                'message' => 'Phone number is already registered!'
            ]);
            $numberCheck->close();
            exit;
        }
        $numberCheck->close();
    } catch (Exception $e) {
        echo json_encode([
            'status' => false,
            'message' => 'Error checking phone number: ' . $e->getMessage()
        ]);
        exit;
    }

    // Hash the password
    $hashed_password = password_hash($password, PASSWORD_DEFAULT);

    // Prepare SQL to insert the data into the database
    try {
        $stmt = $conn->prepare("INSERT INTO signup (name, number, email, password, age, gender, problem) VALUES (?, ?, ?, ?, ?, ?, ?)");

        // Check if the statement was prepared successfully
        if ($stmt === false) {
            echo json_encode([
                'status' => false,
                'message' => 'Error preparing the statement: ' . $conn->error
            ]);
            exit;
        }

        // Bind the parameters
        $stmt->bind_param("ssssiss", $name, $number, $email, $hashed_password, $age, $gender, $problem);

        // Execute the query
        if ($stmt->execute()) {
            echo json_encode([
                'status' => true,
                'message' => 'Signup successful!'
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
            'message' => 'Error during signup: ' . $e->getMessage()
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
