<?php
	include("Header.php");
?>

<body>
	
    <div class="container">
        <div class="row">
            <div class="navbar navbar-default navbar-fixed-top">
                <div class="container">
				    <div class="navbar-brand">Organiser</div>
					<div class="collapse navbar-collapse navHeaderCollapse">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="calendar.php">Calendar</a></li>
                            <li><a class="selected">To-do</a></li>
							<li><a href="budget.php">Budget</a></li>
                            <li id="logoutBtn"><a href="index.php" onclick="logout()">Logout</a></li>
				        </ul>
				    </div>
				</div>
            </div>
        </div>
    </div>
    
    <div id="list" align = "center">
		<h3>To-do List</h3>
		<!--this following code is in a list -->
		<!--the symbol can be changed to be any image and can be changed on hover, http://www.w3schools.com/cssref/pr_list-style-image.asp-->
		<?php
			echo showTodo($link);
		?>
	</div>
</body>

<?php
	include("Footer.php");
?>