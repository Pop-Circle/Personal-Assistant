<?php
	include("Header.php");
?>

<body>
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