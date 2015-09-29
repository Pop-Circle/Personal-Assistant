<?php
	include ("accessor.php");
	if (isset($_SESSION['user']))
	{
		echo $_SESSION['user']['username'];
		session_destroy();
	}
?>