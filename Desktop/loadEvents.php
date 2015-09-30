<?php
	include ("accessor.php");
	$date = json_decode(stripslashes($_REQUEST['obj']));
	echo loadEvents($link,$date->month, $date->day);
?>