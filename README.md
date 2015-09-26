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