<?php
 
/*
 * Following code will get single Events details
 * An event is identified by event id
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();
 
// check for post data
if (isset($_GET["eventID"])) {
    $pid = $_GET['eventID'];
 
    // get a product from products table
    $result = mysql_query("SELECT *FROM eventTable WHERE eventID = $pid");
 
    if (!empty($result)) {
        // check for empty result
        if (mysql_num_rows($result) > 0) {
 
            $result = mysql_fetch_array($result);
 
            $event = array();
            $event["eventID"] = $result["eventID"];
            $event["eventOwnerID"] = $result["eventOwnerID"];
            $event["eventName"] = $result["eventName"];
            $event["eventDate"] = $result["eventDate"];
            $event["eventTime"] = $result["eventTime"];
            $event["eventDesc"] = $result["eventDesc"];
            $event["eventRem"] = $result["eventRem"];
            // success
            $response["success"] = 1;
 
            // user node
            $response["event"] = array();
 
            array_push($response["event"], $event);
 
            // echoing JSON response
            echo json_encode($response);
        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "No event found";
 
            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "No event found";
 
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