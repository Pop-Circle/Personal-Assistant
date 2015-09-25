<?php
	session_start();
	
	$name = "root";
	$password = "";
	//$password = "root";
	$host = "localhost";
	$db = "personalAssistant";
	$link = mysqli_connect($host, $name, $password, $db) or die("Failed to connect.".mysqli_error($link));
	$today = getdate();
	
	function loginCheck($link, $uname, $pw) 	//selects the correct table and data for login check
	{
		$users = mysqli_query($link, "SELECT * FROM users WHERE (username = '$uname') AND (password = '$pw')")or die("Failed to fetch users.".mysqli_error($link));
		
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
	
	function showTodo($link, $uname) 
	{
		$unchecked = mysqli_query($link, "SELECT * FROM tasks WHERE (username = '$uname') AND (checked = 0)");
		$checked = mysqli_query($link, "SELECT * FROM tasks WHERE (username = '$uname') AND (checked = 1)");
		$numUnchecked = mysqli_fetch_array($unchecked);
		$numChecked = mysqli_fetch_array($checked);
		
		if ($numUnchecked > 0)
		{
			while ($item = mysqli_fetch_array($unchecked))
			{
				//echo task name as html
			}
		}
		
		if ($numChecked > 0)
		{
			//maybe output a line of separation between unchecked and checked
			while ($item = mysqli_fetch_array($checked))
			{
				//echo each task name as html
			}
		}
	}
	
	function showEvent($link, $uname)
	{	//this code is psuedo code for the convert part
		$data = mysqli_query($link, "SELECT * FROM events WHERE (username = '$uname') AND convert(time.month) ORDER BY time ASC");
		
		while ($item = mysqli_fetch_array($data))
		{
			//echo all details of item as html
			//title
			//time[hh:mm]
			//time.date[dd:mm:yyyy]
			//venue
			//reminders
			//busy/avaiable
		}
	}
?>