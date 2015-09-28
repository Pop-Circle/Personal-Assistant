<?php
	include("Header.php");
?>

<body>
	<div id="unchecked">
		<h1>To Do List</h1>
		<!--this following code is in a list -->
		<!--the symbol can be changed to be any image and can be changed on hover, http://www.w3schools.com/cssref/pr_list-style-image.asp-->
		<li>item 1</li> <!-- must remove this line when working-->
		<?php
			showTodo($link, $_SESSION["user"].uid);
		?>
	</div>
</body>

<?php
	include("Footer.php");
?>