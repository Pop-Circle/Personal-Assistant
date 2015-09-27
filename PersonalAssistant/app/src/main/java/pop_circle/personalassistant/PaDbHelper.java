package pop_circle.personalassistant;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stashie on 2015/09/24.
 */
public class PaDbHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "personalAssistant";
    private static final String TABLE_TASKS = "tasks";
    private static final String TABLE_USERS= "users";
    private static final String TABLE_BUDGET= "budget";
    private static final String KEY_USERIDTASK ="TaskUserId";
    private static final String KEY_TASKNAME = "taskName";
    //FOR USERS SQL
    private static final String KEY_ID ="id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_CHECKED = "checked";
    //FOR BUDGET SQL
    private static final String KEY_BUDGETIDTASK ="TaskUserId";
    private static final String KEY_INCOME= "incom";
    private static final String KEY_HOUSEHOLD= "household";
    private static final String KEY_FOOD= "food";
    private static final String KEY_CREDIT= "credit";
    private static final String KEY_CLOTHES= "clothes";
    private static final String KEY_LUXURY= "luxury";
    private static final String KEY_CONTRACTS= "contracts";
    private static final String KEY_LOANS= "loans";

    //Jackie : for the event
    private static final String EVENT_TABLE = "eventTable";
    private static final String EVENT_ID ="eventID";
    private static final String EVENT_OWNERID ="eventOwnerID";
    private static final String EVENT_NAME = "eventName";
    private static final String EVENT_DATE = "eventDate";
    private static final String EVENT_TIME = "eventTime";
    private static final String EVENT_DESC = "eventDesc";
    private static final String EVENT_REM = "eventRem"; //If there is a reminder the text will be the time, else it will be "none" in the text

    public PaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String taskSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_TASKS + " ( "
        + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + KEY_USERIDTASK + " TEXT, "
        + KEY_TASKNAME+ " TEXT, "
        + KEY_CHECKED + " INTEGER)";
        db.execSQL(taskSQL);

        String eventSQL = "CREATE TABLE IF NOT EXISTS " + EVENT_TABLE + " ( "
        + EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + EVENT_NAME+ " TEXT, "
        + KEY_TASKNAME+ " TEXT, "
        + EVENT_OWNERID+ " TEXT, "
        + EVENT_TIME+ " TEXT, "
        + EVENT_DATE+ " TEXT, "
        + EVENT_DESC+ " TEXT, "
        + EVENT_REM + " TEXT )";
        db.execSQL(eventSQL);

        String userSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_USERNAME+ " TEXT, "
                + KEY_PASSWORD+ " TEXT, "
                + KEY_EMAIL+ " TEXT)";
        db.execSQL(userSQL);

        String budgetSQL = "CREATE TABLE IF NOT EXISTS " + TABLE_BUDGET + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_BUDGETIDTASK + " TEXT, "
                + KEY_EMAIL+ " TEXT, "
                + KEY_INCOME+ " DECIMAL(13, 2), "
                + KEY_HOUSEHOLD+ " DECIMAL(13, 2), "
                + KEY_FOOD+ " DECIMAL(13, 2), "
                + KEY_CREDIT+ " DECIMAL(13, 2), "
                + KEY_CLOTHES+ " DECIMAL(13, 2), "
                + KEY_LUXURY+ " DECIMAL(13, 2), "
                + KEY_CONTRACTS+ " DECIMAL(13, 2), "
                + KEY_LOANS+ " DECIMAL(13, 2)"
               + ")";
        db.execSQL(budgetSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);

        db.execSQL("DROP TABLE IF EXISTS " + EVENT_TABLE);
        // Create tables again
        onCreate(db);
    }

    public void addTask(Task task, String _user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERIDTASK, _user);
        values.put(KEY_TASKNAME, task.getName()); // task name
// status of task- can be 0 for not done and 1 for done
        values.put(KEY_CHECKED, task.getChecked());
// Inserting Row
        db.insert(TABLE_TASKS, null, values);
        db.close(); // Closing database connection
    }

    public void addUser(String _name, String _pass, String em) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, _name); // username
        values.put(KEY_PASSWORD, _pass);
        values.put(KEY_EMAIL, em);
// Inserting Row
        //To set up budget table
        ContentValues valuesBudget = new ContentValues();
        valuesBudget.put(KEY_EMAIL, em);
        valuesBudget.put(KEY_INCOME, 0);
        valuesBudget.put(KEY_HOUSEHOLD, 0);
        valuesBudget.put(KEY_FOOD, 0);
        valuesBudget.put(KEY_CREDIT, 0);
        valuesBudget.put(KEY_CLOTHES, 0);
        valuesBudget.put(KEY_LUXURY, 0);
        valuesBudget.put(KEY_CONTRACTS, 0);
        valuesBudget.put(KEY_LOANS, 0);
        db.insert(TABLE_BUDGET, null, valuesBudget);
        db.insert(TABLE_USERS, null, values);
        db.close(); // Closing database connection
    }

    public List<Task> getAllTasks(String _user) {
        List<Task> taskList = new ArrayList<Task>();
// Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TASKS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setId(cursor.getInt(0));
                //task.setName(cursor.getString(1));
                task.setName(cursor.getString(cursor.getColumnIndex("taskName")));
                task.setChecked(cursor.getInt(2));

// Adding contact to list
                taskList.add(task);
            } while (cursor.moveToNext());
        }
// return task list
        return taskList;
    }

    public void updateTask(Task task) {
// updating row
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TASKNAME, task.getName());
        values.put(KEY_CHECKED, task.getChecked());
        db.update(TABLE_TASKS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(task.getId())});
    }



/* -----------------------Event db--------------------------------------- */


    /* Adding events details */
    public void addEvent(Event event)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(EVENT_NAME, event.getEventName());
        values.put(EVENT_DESC, event.getEventDesc());
        values.put(EVENT_DATE, event.getEventDate());
        values.put(EVENT_OWNERID, event.getEventOwnerID());
        values.put(EVENT_TIME, event.getEventTime());
        values.put(EVENT_REM, event.getEventRem());

        db.insert(EVENT_TABLE, null, values);
        db.close(); // Closing database connection

    }

    /* Reading event details */
    //Return everything on one row
    public Event getEvent(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(EVENT_TABLE, new String[]{EVENT_ID,
                        EVENT_NAME, EVENT_TIME, EVENT_DESC, EVENT_DATE, EVENT_OWNERID, EVENT_REM}
                , EVENT_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Event event = new Event(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3) ,cursor.getString(4) ,
                cursor.getString(5),cursor.getString(6));
        // return event
        return event;
    }

    //return all the event info on a SPECIFIC day for a SPECIFIC user
    //return all the event info on a SPECIFIC day for a SPECIFIC user
    public List<Event> getAllEvents(String selectedDate, String ownerID)
    {
        List<Event> eventList = new ArrayList<Event>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "eventDate= ? AND eventOwnerID = ?";
        String[] selectionArgs = {selectedDate, ownerID};

        Cursor cursor = db.query(EVENT_TABLE, null, selectQuery, selectionArgs, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Event event = new Event();
                event.setID(Integer.parseInt(cursor.getString(cursor.getColumnIndex("eventID"))));
                event.setEventName(cursor.getString(cursor.getColumnIndex("eventName")));
                event.setEventRem(cursor.getString(cursor.getColumnIndex("eventRem")));
                event.setEventOwnerID(cursor.getString(cursor.getColumnIndex("eventOwnerID")));
                event.setEventTime(cursor.getString(cursor.getColumnIndex("eventTime")));
                event.setEventDate(cursor.getString(cursor.getColumnIndex("eventDate")));
                event.setEventDesc(cursor.getString(cursor.getColumnIndex("eventDesc")));

                // Adding event to list
                eventList.add(event);
            } while (cursor.moveToNext());
        }

        // return event list, list consists of event objects
        return eventList;
    }



    /* Number of events on a specific day */
    public int getEventCount(String dateSelected, String ownerID)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = "eventDate = ? AND eventOwnerID = ?";
        String[] selectionArgs = {dateSelected, ownerID};

        Cursor c = db.query(EVENT_TABLE, null, selection, selectionArgs, null, null, null);
        int result = c.getCount();
        c.close();
        Log.wtf("test", "count " + result);
        return result;
    }

    /* Update an event*/
    public int updateEvent(Event event)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EVENT_NAME, event.getEventName());
        values.put(EVENT_DATE, event.getEventDate());
        values.put(EVENT_TIME, event.getEventTime());
        values.put(EVENT_DESC, event.getEventDesc());
        values.put(EVENT_REM, event.getEventRem());

        // updating row
        return db.update(EVENT_TABLE, values, EVENT_ID + " = ?",
                new String[]{String.valueOf(event.getID())});
    }

    /* Deleting an event */
    public void deleteContact(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(EVENT_TABLE, EVENT_ID + " = ?",
                new String[]{String.valueOf(event.getID())});
        db.close();
    }



}
