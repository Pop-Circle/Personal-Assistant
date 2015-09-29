<?php
	session_start();
	//isset($_SESSION['user'])
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
	
	function showTodo($link) 
	{
		$uid = $_SESSION["user"]["userId"];
		$unchecked = mysqli_query($link, "SELECT * FROM tasks WHERE (TaskUserId = '$uid') AND (checked = 0)")or die("Failed to fetch unchecked.".mysqli_error($link));
		$checked = mysqli_query($link, "SELECT * FROM tasks WHERE (TaskUserId = '$uid') AND (checked = 1)")or die("Failed to fetch checked.".mysqli_error($link));
		
		$numUnchecked = mysqli_num_rows($unchecked);
		$numChecked = mysqli_num_rows($checked);
		
		if ($numUnchecked > 0)
		{
			echo "<list>";
			while ($item = mysqli_fetch_array($unchecked))
			{
				//echo task name as html
				//$ulist += "<li>".$item["taskName"]."</li>";
				echo "<li>".$item["taskName"]."</li>";
			}
			
			echo "</list>";
			//return $ulist;
		}
		
		
		if ($numChecked > 0)
		{
			//maybe output a line of separation between unchecked and checked
			echo "<p>-----------------------------------------</p>";
			while ($item = mysqli_fetch_array($checked))
			{
				//echo each task name as html
				echo "<li>".$item["taskName"]."</li>";
			}
		}
		
	}
	
	function showEvent($today)
	{	//this code is psuedo code for the convert part
		$date = strtotime($today);
		$month = $date.month();
		
		$data = mysqli_query($link, "SELECT * FROM events WHERE (username = '$uname') AND (time = '$date') ORDER BY time ASC");
		
		/*
			$time = strtotime('10/16/2003');
			$newformat = date('Y-m-d',$time);
			echo $newformat;
			// 2003-10-16
		*/
		
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