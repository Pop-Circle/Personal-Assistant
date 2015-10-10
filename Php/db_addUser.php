<?php
//include "db_config.php";
/*
 * Following code will create a new user row
 * All user details are read from HTTP Post Request
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['username']) && isset($_POST['password']) && isset($_POST['email'])) {
 
    $name = $_POST['username'];
    $pass = $_POST['password'];
    $em = $_POST['email'];
 
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql inserting a new row
    $result = mysql_query("INSERT INTO users(username, password, email) VALUES('$name', '$pass', '$em')");
 
    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "User successfully created.";
		$result = mysql_query("INSERT INTO budget(TaskUserId, income, totalExpenses, household, food, credit, clothes, luxury, contracts, loans) VALUES('$name', 0, 0, 0, 0, 0, 0, 0, 0, 0)");
 
        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";
 
        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>