<?php
	include("accessor.php");
	$user = json_decode(stripslashes($_REQUEST['obj']));
	//echo $user->username." | ".$user->password;
	echo loginCheck($link, $user->username, $user->password);
?>