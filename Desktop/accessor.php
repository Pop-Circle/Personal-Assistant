<?php
	session_start();
	//isset($_SESSION['user'])
	$name = "PopCircle";
	$password = "pSaXCaw7";
	//$password = "root";
	$host = "localhost";
	$db = "PopCircle";
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
		$unchecked = mysqli_query($link, "SELECT * FROM tasks WHERE (TaskUserId = '$uid')")or die("Failed to fetch unchecked.".mysqli_error($link));
		
		$numUnchecked = mysqli_num_rows($unchecked);
		
		if ($numUnchecked > 0)
		{
			echo "<div class='container'>";
            echo "<list class = 'list-group'>";
			while ($item = mysqli_fetch_array($unchecked))
			{
				//echo task name as html
				echo "<li class='list-group-item'>".$item["taskName"]."</li>";
			}
			
			echo "</list>";
            echo "</div>";
			//return $ulist;
		}
	}
	
	function loadEvents($link,$month, $day)
	{
		
		$uid = $_SESSION["user"]["userId"];
		$eventDate = $day."-".$month;
		$data = mysqli_query($link, "SELECT * FROM eventtable WHERE (eventOwnerID = '$uid') AND (eventDate = '$eventDate') ORDER BY eventTime ASC")or die("Failed to fetch events.".mysqli_error($link));
		//sorting by eventTime because this only selects events of a specified date(e.g 29-September)
		$numEvent = mysqli_num_rows($data);
		
		if ($numEvent > 0)
		{
			if ($numEvent > 1)
			{
				$result = '{"day":[';
				while ($item = mysqli_fetch_array($data))
					$result .='{"id":"'.$item['eventID'].'","name":"'.$item['eventName'].'","time":"'.$item['eventTime'].'","desc":"'.$item['eventDesc'].'","reminder":"'.$item['eventRem'].'"},';

				$result = rtrim($result, ",");

			return $result.'], "size":"'.$numEvent.'"}';
			}
			else
			{
				$result = "";
				while ($item = mysqli_fetch_array($data))
				{
					$result .='{"id":"'.$item['eventID']
							.'","name":"'.$item['eventName']
							.'","time":"'.$item['eventTime']
							.'","desc":"'.$item['eventDesc']
							.'","reminder":"'.$item['eventRem'].'"},';
				}
				$result = rtrim($result, ",");

				return $result;
			}
		}
		
		return "noEvent";
	}
	
	function loadBudget($link)
	{
		$uid = $_SESSION["user"]["userId"];
		$data = mysqli_query($link, "SELECT * FROM budget WHERE (TaskUserId = '$uid')")or die("Failed to fetch budget.".mysqli_error($link));
		
		$numBudget = mysqli_num_rows($data);
		
		if ($numBudget > 0)
		{
			if ($numBudget == 1)
			{
				$result = "";
				while ($item = mysqli_fetch_array($data))
				{
					$result .='{"id":"'.$item['id']
							.'","income":"'.$item['income']
							.'","totex":"'.$item['totalExpenses']
							.'","household":"'.$item['household']
							.'","food":"'.$item['food']
							.'","credit":"'.$item['credit']
							.'","clothes":"'.$item['clothes']
							.'","luxury":"'.$item['luxury']
							.'","contracts":"'.$item['contracts']
							.'","loans":"'.$item['loans'].'"}';
				}

				return $result;
			}
			else	
				return "invalid";
		}
		else
			return "noBudget";
	}
?>