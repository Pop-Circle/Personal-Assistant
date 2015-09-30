<?php
	include("Header.php");
?>

<body  onload = "start()">
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