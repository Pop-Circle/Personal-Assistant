<?php
	include("accessor.php");
?>

<!DOCTYPE html>
<html>
<head>
	<title>Organiser</title>
	<meta charset = "UTF-8">
	<link rel="stylesheet" type="text/css"  href="style.css">
	<!--<link rel="stylesheet" type="text/css" media="only screen and (min-width: 800px)" href="files/style_med.css">-->
	<!--<link rel="stylesheet" type="text/css" media="only screen and (min-width: 320px)" href="files/style_sml.css">-->
	
	<script type = "text/javascript" src="jquery-1.11.1.min.js"></script>
	
	<div id = "headwrap" align = "center">
		<a href= "index.php" title = "go back to Index">Home</a>
<?php
	if (isset($_SESSION['user']))
	{
		echo '<a href= "calendar.php" title = "go to Calendar">Calendar</a>';
		echo '<a href= "todoList.php" title = "go to To-do List">To-do List</a>';
	}
?>
	</div>
</head>