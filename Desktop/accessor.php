<?php
	session_start();
	
	$name = "root";
	$password = "";
	//$password = "root";
	$host = "localhost";
	$db = "popcircle";
	$link = mysqli_connect($host, $name, $password, $db) or die("Failed to connect.".mysqli_error($link));
	
	function loginCheck($link, $uname, $pw) 	//selects the correct table and data for login check
	{
		$users = mysqli_query($link, "SELECT * FROM members WHERE (username = '$uname') AND (password = '$pw')")or die("Failed to fetch users.".mysqli_error($link));
		
		$userInfo = mysqli_fetch_array($users);
		$numUsers = mysqli_num_rows($users);
		
		if ($numUsers == 1)		//checks whether there is only one user that matched the query
		{
			$_SESSION["uname"] = $uname;
			$_SESSION["user"] = $userInfo;
			return true;
		}
		else
			return false;
	}
	
	function showEvents($link, $uname) //assuming all events are in one table called "events" where we need to select them by username "field"
	{
		$events = mysqli_query($link, "SELECT * FROM events WHERE (username = '$uname')")or die("Failed to fetch events.".mysqli_error($link));
		
		while ($item = mysqli_fetch_array($items))
		{
			
		}
	}
?>