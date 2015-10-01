<?php
 
/*
 * Following code will list all the products
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();
 
// get all products from products table
$result = mysql_query("SELECT * FROM eventTable WHERE eventDate = $date AND eventOwnerID = $ownerID") or die(mysql_error());
 
// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["event"] = array();
 
    while ($row = mysql_fetch_array($result)) {
        // temp user array
		    $product = array();
		    $product["eventID"] = $result["eventID"];
		    $product["eventName"] = $result["eventName"];
		    $product["eventRem"] = $result["eventRem"];
		    $product["eventOwnerID"] = $result["eventOwnerID"];
		    $product["eventTime"] = $result["eventTime"];
		    $product["eventDate"] = $result["eventDate"];
		    $product["eventDesc"] = $result["eventDesc"];
 
        // push single product into final response array
        array_push($response["event"], $product);
    }
    // success
    $response["success"] = 1;
 
    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No products found";
 
    // echo no users JSON
    echo json_encode($response);
}
?>