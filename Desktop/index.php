<?php
	include("Header.php");
?>

<body  onload = "start()">
	<h1 id="month" align="center"></h1>
        <table align="center" border=1 cellpadding=2>
            <tr>
                <th>S</th>
                <th>M</th>
                <th>T</th>
                <th>W</th>
                <th>T</th>
                <th>F</th>
                <th>S</th>
            </tr>
            <tr id="row1">
                <td id="1.0"></td>
                <td id="1.1"></td>
                <td id="1.2"></td>
                <td id="1.3"></td>
                <td id="1.4"></td>
                <td id="1.5"></td>
                <td id="1.6"></td>
            </tr>
            <tr id="row2">
                <td id="2.0"></td>
                <td id="2.1"></td>
                <td id="2.2"></td>
                <td id="2.3"></td>
                <td id="2.4"></td>
                <td id="2.5"></td>
                <td id="2.6"></td>
            </tr>
            <tr id="row3">
                <td id="3.0"></td>
                <td id="3.1"></td>
                <td id="3.2"></td>
                <td id="3.3"></td>
                <td id="3.4"></td>
                <td id="3.5"></td>
                <td id="3.6"></td>
            </tr>
            <tr id="row4">
                <td id="4.0"></td>
                <td id="4.1"></td>
                <td id="4.2"></td>
                <td id="4.3"></td>
                <td id="4.4"></td>
                <td id="4.5"></td>
                <td id="4.6"></td>
            </tr>
            <tr id="row5">
                <td id="5.0"></td>
                <td id="5.1"></td>
                <td id="5.2"></td>
                <td id="5.3"></td>
                <td id="5.4"></td>
                <td id="5.5"></td>
                <td id="5.6"></td>
            </tr>
        </table>
        
        <br/>
        
        <div align="center" id="buttons">
            <button type="button" onclick="decMonth()"><---</button>
            <button type="button" onclick="incMonth()">---></button>
        </div>
</body>

<?php
	include("Footer.php");
?>