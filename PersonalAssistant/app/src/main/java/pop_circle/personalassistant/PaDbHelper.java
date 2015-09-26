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
}
