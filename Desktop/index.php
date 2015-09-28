<?php
	include("Header.php");
	
	if (isset($_SESSION['user']))
		echo '<body>
				<p>You are already logged in</p>
				<button onclick = "logout()">Logout</button>
				<button onclick = "backToCalendar()">Back</button>
			</body>';
	else
	{
		echo '<body>
				<div>
					<h1>Login</h1>
					<input id = "lg" name = "login" type = "text" placeholder = "Username" autofocus required/></br>
					<input id = "pw" name = "password" type = "password" placeholder = "Password" required/></br>
					<button onclick = "login()">Login</button>
				</div>
			</body>';
	}
?>

<?php
	include("Footer.php");
?>