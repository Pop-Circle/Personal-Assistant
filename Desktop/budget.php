<?php
	include ("Header.php");
?>

<body  onload = "loadBudget()">
    
    <div class="container">
        <div class="row">
            <div class="navbar navbar-default navbar-fixed-top">
                <div class="container">
				   <div class="navbar-brand">Organiser</div>
					<div class="collapse navbar-collapse navHeaderCollapse">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="calendar.php">Calendar</a></li>
                            <li><a href="todoList.php">To-do</a></li>
							<li><a class="selected">Budget</a></li>
                            <li id="logoutBtn"><a href="index.php" onclick="logout()">Logout</a></li>
				        </ul>
				    </div>
				</div>
            </div>
        </div>
    </div>
    
    <!--<h1 id="month" align="center"></h1>-->
   <div align = "center" id = "budgetContainer">
		<p id = "budgetData"><?php
			echo loadBudget($link);
		?></p>
	</div>
	<div id="chartContainer" style="height: 300px; width: 100%;"></div>
</body>


<?php
	include("Footer.php");
?>