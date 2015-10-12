<?php
	include("Header.php");
	
	if (isset($_SESSION['user']))
		header('location: calendar.php');
	else
	{
		echo '
            <body>
                <div class="container">
                    <div class="row">
                        <div class="navbar navbar-default navbar-fixed-top">
                            <div class="container">
                                <div class="navbar-brand">
                                    <p>Organiser</p>
                                </div>
                            </div>					
                        </div>
                    </div>
                    <div class="row">
                        <div class="navbar navbar-default navbar-fixed-bottom">
                            <div class="container">
                                <p class="navbar-text pull-left">Copyright &copy;  Pop-Circle 2015</p>
                            </div>
                        </div>
                    </div>
                    <div class="row col-md-11 col-centered" id="body-content">
                        <div class="col-md-7">
                            <h1>Welcome!</h1>
                            <h2>Pop-Circles Personal-Assistant</h2>
                            <h2>A scheduling application tailored to students</h2>
                        </div>
                        <div>                        
                            
                            <div id="lgContainer" class="form-horizontal pull-right" >
								<h3>Login</h3>
                                <div class="form-group">
                                    <input type="text" id="lg" name="login" class="form-control" placeholder="username" required>
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control" id="pw" name="password" required placeholder="password">
                                </div>
                                <button class="btn btn-default center-block" onclick = "login()">Log in</button>
                            </form>
                        
                        </div>
                    </div>
                </div>
            </body>
            ';
	}
?>

<?php
	include("Footer.php");
?>