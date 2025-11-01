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
    $adminname = isset($_POST['adminname']) ? trim($_POST['adminname']) : '';
    $number = isset($_POST['number']) ? trim($_POST['number']) : '';
    $email = isset($_POST['email']) ? trim($_POST['email']) : '';
    $password = isset($_POST['password']) ? trim($_POST['password']) : '';
    $adminid = isset($_POST['adminid']) ? trim($_POST['adminid']) : '';

    // Validate the input data
    if (empty($adminname) || empty($number) || empty($email) || empty($password) || empty($adminid)) {
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
        $emailCheck = $conn->prepare("SELECT * FROM adminsignup WHERE email = ?");
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
        $numberCheck = $conn->prepare("SELECT * FROM adminsignup WHERE number = ?");
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

    // Check if the admin ID already exists in the database
    try {
        $adminIdCheck = $conn->prepare("SELECT * FROM adminsignup WHERE adminid = ?");
        $adminIdCheck->bind_param("s", $adminid);
        $adminIdCheck->execute();
        $adminIdResult = $adminIdCheck->get_result();
        if ($adminIdResult->num_rows > 0) {
            echo json_encode([
                'status' => false,
                'message' => 'Admin ID is already registered!'
            ]);
            $adminIdCheck->close();
            exit;
        }
        $adminIdCheck->close();
    } catch (Exception $e) {
        echo json_encode([
            'status' => false,
            'message' => 'Error checking admin ID: ' . $e->getMessage()
        ]);
        exit;
    }

    // Hash the password (using bcrypt)
    try {
        $hashedPassword = password_hash($password, PASSWORD_BCRYPT);
    } catch (Exception $e) {
        echo json_encode([
            'status' => false,
            'message' => 'Error hashing password: ' . $e->getMessage()
        ]);
        exit;
    }

    // Prepare SQL to insert the data into the database
    try {
        $stmt = $conn->prepare("INSERT INTO adminsignup (adminname, number, email, password, adminid) VALUES (?, ?, ?, ?, ?)");

        // Check if the statement was prepared successfully
        if ($stmt === false) {
            echo json_encode([
                'status' => false,
                'message' => 'Error preparing the statement: ' . $conn->error
            ]);
            exit;
        }

        // Bind the parameters
        $stmt->bind_param("sssss", $adminname, $number, $email, $hashedPassword, $adminid);

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
