<?php
 
/*
 * Following code will update event
 * A product is identified by eventID
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['eventID']) && isset($_POST['eventOwnerID']) && isset($_POST['eventName']) && isset($_POST['eventDate']) && isset($_POST['eventTime'])
		&& isset($_POST['eventDesc']) && isset($_POST['eventRem'])) {
 
    $eventOwnerID = $_POST['eventOwnerID'];
    $eventName = $_POST['eventName'];
    $eventDate = $_POST['eventDate'];
    $eventTime = $_POST['eventTime'];
    $eventDesc = $_POST['eventDesc'];
    $eventRem = $_POST['eventRem'];
    $eventID = $_POST['eventID'];
 
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();
 
    // mysql update row with matched pid
    $result = mysql_query("UPDATE eventTable SET 
			eventOwnerID = '$eventOwnerID', eventName = '$eventName', 
			eventDate = '$eventDate', eventTime = '$eventTime', 
			eventDesc = '$eventDesc', eventRem = '$eventRem', 
			WHERE pid = $eventID");
 
    // check if row inserted or not
    if ($result) {
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "event successfully updated.";
 
        // echoing JSON response
        echo json_encode($response);
    } else {
 
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";
 
    // echoing JSON response
    echo json_encode($response);
}
?>