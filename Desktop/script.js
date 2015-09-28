var monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
var weekDays = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
//var daysInMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
//var d = new Date(2015, 11, 12);
var d = new Date();

function start()
{
	clearTable();
	//$("#month").html(monthNames[d.getMonth()] + " " + d.getYear());
	$("#month").html(monthNames[d.getMonth()]);
	var month = d.getMonth();
	var year = d.getYear();
	if (year < 1000){
		year+=1900;
	}
	var firstDayInstance = new Date(year, month, 1);
	var firstDay = firstDayInstance.getDay();
	firstDayInstance = null;

	var count = 1;
	var x = firstDay;
	document.getElementById("1."+firstDay).innerHTML = "1";
	for(var i=1; i<=5; i++){
		//while(x <= 6 && count <= daysInMonth[d.getMonth()]){
		while(x <= 6 && count <= daysInMonth(d)){
			document.getElementById(i+"."+x).style.clear;
			if(count == d.getDate()){
				document.getElementById(i+"."+x).innerHTML = count;
				document.getElementById(i+"."+x).style.background = "grey";
				document.getElementById(i+"."+x).setAttribute('onclick', 'doThis();');
				//ajax call for showEvent(d.toString);
			};
			document.getElementById(i+"."+x++).innerHTML = count++;
		};
		x = 0;
	}
}

function daysInMonth(date)
{
	if (date.month%2 == 0)	//even number (January is 0)
		return maxDays = 31;
	else
	{
		if (date.month == 1)	//odd number
		{
			if (date.year%4 == 0)	//is February
				return maxDays = 29;	//leap year
			else
				return maxDays = 28;
		}
		else
			return 30;
	}
}

function clearTable()
{
	var tRows = $("td").length;
	//alert(tRows);
	var i;
	for (i in $("td"))
	{
		$("td").css("background-color", "");
		$("td").empty();
	}
}

function doThis(){
	window.location="today.html";
}


function decMonth(){
	var month = d.getMonth();
	var year = d.getYear();
	var day = d.getDate();
	if (month < 0)
		d = new Date(year-1, 11,day);
	else
		d = new Date(year, month-1,day);
	
	start();
}

function incMonth(){

	var month = d.getMonth();
	var year = d.getYear();
	var day = d.getDate();
	if (month > 11)
		d = new Date(year+1, 00,day);
	else
		d = new Date(year, month+1,day);
	
	start();
}

function login()
{
	var lg = $("#lg").val();
	var pw = $("#pw").val();
	
	if (lg == "" || pw == "")
		alert("Please fill in both fields");
	else
	{
		var user = {
			"username":lg,
			"password":pw
		};
		
		var strUser = JSON.stringify(user);
		//alert(strUser);
		$.ajax({
			type: "POST",
			url: "loginCheck.php",
			data: {obj: strUser},
			success:function(data)
			{
				if (data == true)
				{
					backToCalendar();
				}
				else
				{
					alert("Credentials incorrect");
					location.reload();
				}
			}
		});
	}
}

function backToCalendar()
{
	window.location="calendar.php";
}

function logout()
{
	$.ajax({
		type: "POST",
		url: "logout.php",
		success:function(data)
		{
			alert("Logged out");
			location.reload();
		}
	});
}