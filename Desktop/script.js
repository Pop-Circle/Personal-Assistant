var monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
var weekDays = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
//var daysInMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
//var d = new Date(2015, 11, 12);
var d = new Date();
/*
var month = d.getMonth();
var year = d.getYear();
*/

function loadBudget() {
	var dataStr = $("#budgetData").text();
	//alert(dataStr);
	
	var data = JSON.parse(dataStr);
	var chart = new CanvasJS.Chart("chartContainer",
	{
		title:{
			text: "Budget"
		},
		legend: {
			maxWidth: 800,
			itemWidth: 250
		},
		data: [
		{
			type: "pie",
			showInLegend: true,
			legendText: "{indexLabel}",
			dataPoints: [
				{ y: data.clothes/data.totex*100, indexLabel: "Clothes: R" + data.clothes},
				{ y: data.contracts/data.totex*100, indexLabel: "Contracts: R" + data.contracts},
				{ y: data.credit/data.totex*100, indexLabel: "Credit: R" + data.credit},
				{ y: data.food/data.totex*100, indexLabel: "Food: R" + data.food},
				{ y: data.household/data.totex*100, indexLabel: "Household: R" + data.household},
				{ y: data.luxury/data.totex*100, indexLabel: "Luxury: R" + data.luxury}]
		}
		]
	});
	chart.render();
}

function start()
{
	/*
	clearTable();
	//$("#month").html(monthNames[d.getMonth()] + " " + d.getYear());
	
	
	if (year < 1000){
		year+=1900;
	}
	
	$("#month").html(monthNames[d.getMonth()] + " " + year);
	
	var firstDayInstance = new Date(year, month, 1);
	var firstDay = firstDayInstance.getDay();
	firstDayInstance = null;

	var count = 1;
	var x = firstDay;
	document.getElementById("1."+firstDay).innerHTML = "1";
	for(var i=1; i<=5; i++){
		//while(x <= 6 && count <= daysInMonth[d.getMonth()]){
		while(x <= 6 && count <= maxDays(d)){
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
	*/
	//lightbox();
	calendar(d.getMonth());
}

/* borrowed from http://stackoverflow.com/questions/17391886/a-plugin-to-create-html-calendars-tables-easily*/
function calendar(month) {

    //Variables to be used later.  Place holders right now.
    var padding = "";
    var totalFeb = "";
    var i = 1;
    var testing = "";

    var current = new Date();
    var cmonth = current.getMonth(); // current (today) month
    var day = current.getDate();
    var year = current.getFullYear();
    var tempMonth = month + 1; //+1; //Used to match up the current month with the correct start date.
    var prevMonth = month - 1;

    //Determing if Feb has 28 or 29 days in it.  
    if (month == 1) {
        if ((year % 100 !== 0) && (year % 4 === 0) || (year % 400 === 0)) {
            totalFeb = 29;
        } else {
            totalFeb = 28;
        }
    }

    // Setting up arrays for the name of the months, days, and the number of days in the month.
    var totalDays = ["31", "" + totalFeb + "", "31", "30", "31", "30", "31", "31", "30", "31", "30", "31"];

    // Temp values to get the number of days in current month, and previous month. Also getting the day of the week.
    var tempDate = new Date(tempMonth + ' 1 ,' + year);
    var tempweekday = tempDate.getDay();
    var tempweekday2 = tempweekday;
    var dayAmount = totalDays[month];

    // After getting the first day of the week for the month, padding the other days for that week with the previous months days.  
	//IE, if the first day of the week is on a Thursday, then this fills in Sun - Wed with the last months dates, counting down from the last day on Wed, until Sunday.
    while (tempweekday > 0) {
        padding += "<div class='tData premonth'></div>";
        //preAmount++;
        tempweekday--;
    }
    // Filling in the calendar with the current month days in the correct location along.
    while (i <= dayAmount) {

        // Determining when to start a new row
        if (tempweekday2 > 6) {
            tempweekday2 = 0;
            padding += "</div><div class='tRow'>";
        }

        // checking to see if i is equal to the current day, if so then we are making the color of that cell a different color using CSS. Also adding a rollover effect to highlight the day the user rolls over. This loop creates the actual calendar that is displayed.
        loadEvents(i);
		if (i == day && month == cmonth) {
            padding += "<div class='tData currentday'>" + i + "</div>";
        } else {
            padding += "<div class='tData currentmonth'>" + i + "</div>";
        }
        tempweekday2++;
        i++;
    }

	var calendarTable = "<h2>" + monthNames[month] + " " + year + "</h2>";
    // Outputing the calendar onto the site.  Also, putting in the month name and days of the week.
	calendarTable += 	"<div class='tTable calendar' align = 'center'>";
    calendarTable += 		"<div class='tRow weekdays'>" + 
								"<div class = 'tData'>Sunday</div>" + 
								"<div class = 'tData'>Monday</div>" + 
								"<div class = 'tData'>Tuesday</div>" + 
								"<div class = 'tData'>Wednesday</div>" + 
								"<div class = 'tData'>Thursday</div>" + 
								"<div class = 'tData'>Friday</div>" + 
								"<div class = 'tData'>Saturday</div>" +
							"</div>";
    calendarTable += 		"<div class = 'tRow'>";
    calendarTable += padding;
    calendarTable += 		"</div>" + 
						"</div>";
    $("#calendar").html(calendarTable);
	
	$(".tData").click(function(){
		
		var wh = $(this).width() + " | " + $(this).height();
		alert(wh);
	});
	
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
	window.location="todoList.php";
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

function thisMonth()
{
	d = new Date();
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

function logout()
{
	$.ajax({
		type: "POST",
		url: "logout.php",
		success:function(data)
		{
			alert(data + " Logged out");
			location.reload();
		}
	});
}

function backToCalendar()
{
	window.location="calendar.php";
}

function loadEvents(day)
{
	var result;
	var date = {
		"day": day,
		"month": monthNames[d.getMonth()]
	};
	
	var strDate = JSON.stringify(date);
	
	$.ajax({
		type: "GET",
		url: "loadEvents.php",
		data: {obj: strDate},
		success:function(data)
		{
			if (data != "noEvent")
				populateDay(data, day);
		}
	});
}

function populateDay(info, dd) //dd is date of month
{
	$("div").each(function(index){
		if ($(this).html() == dd)
		{
			//console.log(info);
			var details = JSON.parse(info);
			if (details.size)
			{
				var event = "<div class = 'eDay'>";
				for (stuff in details.day)
				{
					event += "<list class = 'eList' id = 'event"+details.day[stuff].id+"'>"+
								"<li> "+ details.day[stuff].time +": " + details.day[stuff].name + "</li>" +
								"<li class = 'eDetails'>" + JSON.stringify(details.day[stuff])+ "</li>" +
							"</list>";
					
				}
				$(this).append(event+="</div>");
			}
			else
			{
				var event = "<div class = 'eDay'><list class = 'eList' id = 'event"+details.id+"'>"+
								"<li> "+ details.time +": " + details.name + "</li>" +
								"<li class = 'eDetails'>" + JSON.stringify(details) + "</li>" +
							"</list></div>";
				$(this).append(event);
			}
		}
	});
	
	$(".eList").click(function(){
		
		var data = JSON.parse($(this).find(".eDetails").text());
		
		var eventDetails = 	"<list class = 'eList'>" +
								"<li> Name: " + data.name + "</li>" +
								"<li> Time: " + data.time + "</li>" +
								"<li> Description: " + data.desc + "</li>" +
								"<li> Remind at: " + data.reminder + "</li>" +
							"</list>";
		$("#eventDetails").html(eventDetails);
		
		lightbox();
	});
}

function lightbox()
{
	$("#lightbox").toggle();
}












