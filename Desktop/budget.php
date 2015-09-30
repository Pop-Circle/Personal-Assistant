<?php
	include ("Header.php");
?>

<body  onload = "loadBudget()">
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