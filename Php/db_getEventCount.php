<?php
 
/*
 * Following code will get the total amount of events on a date
 */
 
// array for JSON response
$response = array();
 
// include db connect class
require_once __DIR__ . '/db_connect.php';
 
// connecting to db
$db = new DB_CONNECT();
 if (isset($_GET["eventDate"]) && isset($_GET["eventOwnerID"])) {
	$pid = $_GET['pid'];
	// get all products from products table
	$result = mysql_query("SELECT * FROM eventTable WHERE eventDate= AND eventOwnerID=") or die(mysql_error());
	 
	$num = mysql_num_rows($result);

	$response["count"] = $num;
	echo json_encode($num);
	
}
?>