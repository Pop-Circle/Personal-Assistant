<?php
	include("Header.php");
?>

<body  onload = "start()">
    
    <div class="container">
        <div class="row">
            <div class="navbar navbar-default navbar-fixed-top">
                <div class="container">
				    <div class="navbar-brand">Organiser</div>
					<div class="collapse navbar-collapse navHeaderCollapse">
                        <ul class="nav navbar-nav navbar-right">
                            <li><a href="#" class="selected">Calendar</a></li>
                            <li><a href="todoList.php">To-do</a></li>
							<li><a href="budget.php">Budget</a></li>
                            <li id="logoutBtn"><a href="index.php" onclick="logout()">Logout</a></li>
				        </ul>
				    </div>
				</div>
            </div>
        </div>
    </div>
	<!--<h1 id="month" align="center"></h1>-->
    
   <div align = "center">
		<div id = "calendar">
		</div>
		
		<button type="button" title = "Previous Month" onclick="decMonth()">
			<img id = "calLeft" src = "icons/arrowL-small.png"/>
		</button>
		<button type="button" title = "This Month" onclick="thisMonth()">Today</button>
		<button type="button" title = "Next Month" onclick="incMonth()">
			<img id = "calLeft" src = "icons/arrowR-small.png"/>
		</button>
	</div>
	
	<div id = "lightbox" onclick = "lightbox()">
		<div  id = 'eventDetails'></div>
	</div>
</body>

<?php
	include("Footer.php");
?>