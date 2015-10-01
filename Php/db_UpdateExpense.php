<?php
 
/*
 * Following code will update a product information
 * A product is identified by product id (pid)
 */
 
// array for JSON response
$response = array();
 
// check for required fields
if (isset($_POST['TaskUserId']) && isset($_POST['tag']) && isset($_POST['val'])) {
 
    $TaskUserId= $_POST['TaskUserId'];
    $tag = $_POST['tag'];
    $val = $_POST['val'];
 
    // include db connect class
    require_once __DIR__ . '/db_connect.php';
 
    // connecting to db
    $db = new DB_CONNECT();

	////////Get expense
	$resultTE = mysql_query("SELECT totalExpenses FROM budget WHERE TaskUserId = $TaskUserId");
	$resultTE = mysql_fetch_array($resultTE); //From db_getexpense.db, but with no error checking
	$newVAl = val + $resultTE['totalExpenses'];
	
	$increase = 0;
	$result = mysql_query("SELECT * FROM budget WHERE TaskUserId = $TaskUserId");
	$result = mysql_fetch_array($resultHH);
	switch($tag)
	{
		case "Household":{
			$increase = $val + $result['household'];
			$column='household';
			break;
		}
		case "Food":{
			$increase = $val + $result['food'];
			$column='food';
			break;
		}
		case "Credit":{
			$increase = $val + $result['credit'];
			$column='credit';
			break;
		}
		case "Clothes":{
			$increase = $val + $result['clothes'];
			$column='household';
			break;
		}
		case "Luxury":{
			$increase = $val + $result['luxury'];
			$column='household';
			break;
		}
		case "Contracts":{
			$increase = $val + $result['contracts'];
			$column='contracts';
			break;
		}
		case "Bonds/Loans":{
			$increase = $val + $result['loans'];
			$column='loans';
			break;
		}
	}

    // mysql update row with matched pid
    $result = mysql_query("UPDATE budget SET "+$column+" = '$increase' WHERE TaskUserId = $TaskUserId");
 
    // check if row inserted or not
    if ($result) {
        // successfully updated
        $response["success"] = 1;
        $response["message"] = "Product successfully updated.";
 
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