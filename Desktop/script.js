var monthNames = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
var weekDays = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
var daysInMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
/*might want to consider leap years as well, not sure how your array works so not sure how to change it
	but instead of an array, try make it that even numbers in monthNames[] are 31 and odd numbers are 30 except for 1
	
	Example:		array					month	maxDays	isFeb
					monthNames[0] -> January 	31
					monthNames[3] -> April		30 			3!= 1
					monthNames[1] -> February	28 			1==1
				leap year check: 	year				leap year	MaxDaysFeb
										2016%4 = 0 	true			29
										2015%4 != 0	false			28
										
										
	basically
	if (date.month%2 == 0)
	{	
		//even number
		maxDays = 31;
	}
	else
	{
		//odd number
		if (date.month == 1)
		{
			//is February
			if (date.year%4 == 0)
			{
				//leap year
				maxDays = 29;
			}
			else
				maxDays = 28;
		}
	}
		
*/
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
				//ajax call for showEvent(d.toString);
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

function login()
{
	var lg = document.getElementById("lg").value;
	var pw = document.getElementById("pw").value;
	
	/*ajax call to loginCheck();
		if returns true go to calendar with events
	*/
}