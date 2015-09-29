<?php
	include("Header.php");
?>

<body  onload = "start()">
	<h1 id="month" align="center"></h1>
        <br/>
       <div align = "center">
			<div id = "calendar"></div>
			
			<button type="button" onclick="decMonth()"><---</button>
			<button type="button" onclick="incMonth()">---></button>
		</div>
</body>

<?php
	include("Footer.php");
?>