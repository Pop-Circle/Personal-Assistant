package pop_circle.personalassistant;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

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
    private static final String KEY_ID ="id";
    private static final String KEY_TASKNAME = "taskName";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_CHECKED = "checked";

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
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_TASKS + " ( "
        + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + KEY_TASKNAME+ " TEXT, "
        + KEY_CHECKED + " INTEGER)";
        db.execSQL(sql);

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

        String sql2 = "CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + KEY_USERNAME+ " TEXT, "
                + KEY_PASSWORD+ " TEXT, "
                + KEY_EMAIL+ " TEXT)";
        db.execSQL(sql2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);

        db.execSQL("DROP TABLE IF EXISTS " + EVENT_TABLE);
        // Create tables again
        onCreate(db);
    }

    public void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
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
        db.insert(TABLE_USERS, null, values);
        db.close(); // Closing database connection
    }

    public List<Task> getAllTasks() {
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
                task.setName(cursor.getString(1));
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

        Cursor cursor = db.query(EVENT_TABLE, new String[] { EVENT_ID,
                        EVENT_NAME, EVENT_TIME, EVENT_DESC, EVENT_DATE ,EVENT_OWNERID,  EVENT_REM  }
                        , EVENT_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Event event = new Event(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2),cursor.getString(3) ,cursor.getString(4) ,
                cursor.getString(5),cursor.getString(6));
        // return event
        return event;
    }

    //return all the event info on a SPECIFIC day for a SPECIFIC user
    public List<Event> getAllEvents(String selectedDate, String ownerID)
    {
        List<Event> eventList = new ArrayList<Event>();


        String selectQuery = "SELECT  * FROM " + EVENT_TABLE + " WHERE eventDate= "+ selectedDate
                + " AND eventOwnerID= " + ownerID;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Event event = new Event();
                event.setID(Integer.parseInt(cursor.getString(0)));
                event.setEventName(cursor.getString(1));
                event.setEventTime(cursor.getString(2));
                event.setEventDesc(cursor.getString(3));
                event.setEventDate(cursor.getString(4));
                event.setEventOwnerID(cursor.getString(5));
                event.setEventRem(cursor.getString(6));

                // Adding event to list
                eventList.add(event);
            } while (cursor.moveToNext());
        }

        // return event list, list consists of event objects
        return eventList;
    }

    /* Number of events on a specific day */
    public int getEventCount(String selectedDate, String ownerID)
    {
        String countQuery = "SELECT  * FROM " + EVENT_TABLE + " WHERE eventDate= "+ selectedDate
                + " AND eventOwnerID= " + ownerID;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
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
