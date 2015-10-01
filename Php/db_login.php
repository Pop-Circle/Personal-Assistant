<?php
 
/*
 * Following code will get single product details
 */
 
// array for JSON response
//$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();
 
// check for post data
if (isset($_GET["userId"]) && isset($_GET["username"]) && isset($_GET["password"])) {
    $userId = $_GET['userId'];
    $username = $_GET['username'];
    $password = $_GET['password'];
 
    // get a product from products table
    $result = mysql_query("SELECT *FROM products WHERE pid = $pid");
 
    if (!empty($result)) {
	$response['success'] = 1;
	echo json_encode($response);
    } else {
        // no product found
        $response['success'] = 0;
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>