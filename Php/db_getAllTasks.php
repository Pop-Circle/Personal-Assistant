<?php
 
/*
 * Following code will get all task details
 * A product is identified by TaskUserId
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();
 
// check for post data
if (isset($_GET["TaskUserId"])) {
    $pid = $_GET['TaskUserId'];
 
    // get a product from products table
    $result = mysql_query("SELECT *FROM tasks WHERE TaskUserId = $pid");
 
    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {
 
            $result = mysql_fetch_array($result);
 
            $product = array();
            $product["TaskUserId"] = $result["TaskUserId"];
            $product["taskName"] = $result["taskName"];
            $product["checked"] = $result["checked"];
            $product["id"] = $result["id"];
            // success
            $response["success"] = 1;
 
            // user node
            $response["task"] = array();
 
            array_push($response["task"], $product);
 
            // echoing JSON response
            echo json_encode($response);
        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "No product found";
 
            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "No product found";
 
        // echo no users JSON
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