# Personal-Assistant


-2015-09-22 : Jackie 

	Removed app bar 	
	Added calander, no functionality yet 
	
-2015-09-24 : Jackie 

	Using Caldroid for calendar 	
	Intergrated Caldroid into the app	
	Added add_event activity

-2015-09-25 : Jackie

	Added add event form
	Can get data from the event form 
		(Scroll down to see the Save event button)
		
	Variables for the add event form data
		hour_rem  	reminder hour
		minute_rem  	reminder minute
		hour_time  	Event time
		minute_time  	Event Minute
		eventName 	Name of the Event
		desc 			Description if any
		checked 		If reminder is set
		dateSelected 	Date selected
		monthSelected	Month selected (Month name)
	
	Agenda Page : used to view, delete and edit entries
		Editing an entry will take you to creating an entry to edit it
		Short press to edit
		Long press to delete (Not yet implemented yet)
		
	
-2015-09-25 : Dave
	
	Added Desktop folder
	created generic php code
	Colour scheme also in Desktop
	Took some of brian's JS and put it in php
	working on fully incorporating HTML structure to php.
	Using Xampp as sandbox

-2015-09-25 : Tilesh

	Added the following activities:
		PaDbHelper-To help with easily managing the Database
		Task-A basic task class to be used within the TaskManager as an object helper
		TaskManager-The class used to create the tasks by user and store it in a db
		ListItem-A class to display the task items in list form with check boxes
		Personalize-A class to allow the user to set sleep and wake times if chosen
		silent-A class to silence device
		unSilence-A class to unmute device at time of own choice of user
	Added following Layouts
		activity_task_manager.xml
		listitems.xml
		personalize_device.xml
	Basic Changes:
	I have created the tasker or to do list along with its database
	processes including checking off of items. I have also made a dbHelper
	to allow for easy control over the database in the near future. I have
	created the Personalization of the mobile device to allow for the user
	to choose sleep and wake up times for the device. And i got Github
	working :D.
	P.S if doesnt run, remove proxy details from gradle.properties but gitignore should ignore this anyway
	

-2015-19-26 : Jackie
	
	Added database functionality for events in PaDbHelper.java
		DB Functions implemented (not tested yet)
			Add event
			Get specifc event info
			Delete specific event
			Get total number of events for a specific date
	Can now add events to the db
	Able to get the amount of events on the date clicked for the specified user
	
-2015-09-26 : Tilesh

	Added Register Activity with very basic register functionality
	Updated PaDBHelper to work with user db
	Created layouts for login and register
	
-2015-09-27 : Jackie
	
	Able to display all events and retrieve its information on the selected date
	Can now delete an entry by long tap and selecting delete
	Pressing on an activity will now take you to the edit page
	
-2015-09-27 : Tilesh

	Completed styling for all my pages.
	Completed the login.java and completed functionality with login and register including with the DB.
	Started the budget aspect of the app.
	Styled budget aspect of the app.
	added some db functionality, not yet working.
	Created class for housing users ID once logged in and making it global.
	
-2015-09-28 : Jackie
	
	Can now edit and save edited events
	All dates with events are now shown in the calendar
	
-2015-09-28 : Dave
	
	Can now log in and out of desktop version
	fixed calendar display but the date correlation to real date is incorrect when month is changed
	
-2015-09-29 : Dave
	
	Desktop Version
		to do list is shown when today's date is clicked on
		separation of unchecked and checked for to do list implemented
		fixed calendar logic with borrowed code, just need Brian to approve of replaement
		calendar only works for 2015
		added basic styling

-2015-09-29 : Jackie
	
	Implemented the reminder for events
		Shows a notification when the reminder is triggered
	
-2015-09-29 : Tilesh

	Fully implemented Budget feature
	Adapted Task Manager to work with new tables
	Task update doesn't work for some odd reason
	Created Home activity and layout to travel between Activities

-2015-09-30 : Dave
	
	Desktop version
		Events are listed on individual days with all details
		trying to implement so that full details only display  onclick
		styling still needs to be done
		budget for Desktop version still needs to be done
		
	Desktop version
		Budget is done
		Full details for events on click is done
		styling is done but needs to be tweeked
		
-2015-09-30 : Jackie

	Calendar complete(somewhat)
	Changed the task mangaer to delete upon checking items

-2015-10-01 : Jackie
	
	Updated the overall colour of the app
	Changed padding and textsize so all activity is consistent
