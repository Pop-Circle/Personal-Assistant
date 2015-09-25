var monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
var weekDays = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
var daysInMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
//var d = new Date(2015, 11, 12);
var d = new Date();

function start()
{
	document.getElementById("month").innerHTML = monthNames[d.getMonth()];
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
		while(x <= 6 && count <= daysInMonth[d.getMonth()]){
			document.getElementById(i+"."+x).style.clear;
			if(count == d.getDate()){
				document.getElementById(i+"."+x).innerHTML = count;
				document.getElementById(i+"."+x).style.background = "grey";
				document.getElementById(i+"."+x).setAttribute('onclick', 'doThis();');
			};
			document.getElementById(i+"."+x++).innerHTML = count++;
		};
		x = 0;
	}
}

function doThis(){
	window.location="today.html";
}


function decMonth(){
	//alert("decMonth()");
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
	//alert("decMonth()");
	/*
	var month = d.getMonth();
	var year = d.getYear();
	var day = d.getDate();
	if (month > 11)
		d = new Date(year+1, 00,day);
	else
		d = new Date(year, month+1,day);
	
	start();
	*/
}