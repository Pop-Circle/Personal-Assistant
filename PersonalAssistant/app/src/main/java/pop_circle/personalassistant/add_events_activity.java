package pop_circle.personalassistant;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class add_events_activity extends AppCompatActivity {

    private ArrayAdapter<String> mAdapter;
    private ListView mDrawerList;

    static final int DIALOGUE_ID = 0;
    int hour_rem;
    int minute_rem;
    int hour_time;
    int minute_time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_events_activity);

        //This gets the date and month that the user clicked on, passed from CalenderActivity.java
        Intent iDate = getIntent();
        Bundle bDate = iDate.getBundleExtra("dateSelected");
        int datePassed = bDate.getInt("date");
        int monthPassed = bDate.getInt("month");


        String monthLine = "";
        switch(monthPassed)
        {
            case 1:
                monthLine = "January";
                break;
            case 2:
                monthLine = "February";
                break;
            case 3:
                monthLine = "March";
                break;
            case 4:
                monthLine = "April";
                break;
            case 5:
                monthLine = "May";
                break;
            case 6:
                monthLine = "June";
                break;
            case 7:
                monthLine = "July";
                break;
            case 8:
                monthLine = "August";
                break;
            case 9:
                monthLine = "September";
                break;
            case 10:
                monthLine = "October";
                break;
            case 11:
                monthLine = "November";
                break;
            case 12:
                monthLine = "December";
                break;
        }

        TextView dateMonthLabel = (TextView) findViewById(R.id.monthYear);
        dateMonthLabel.setText(datePassed + " " + monthLine);

        saveEvent();
        timePickerDialogue();




    }


    private void timePickerDialogue()
    {
        final CheckBox reminderCB = (CheckBox)findViewById(R.id.reminderCheckBox);
        reminderCB.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean checked = ((CheckBox) v).isChecked();

                if(checked) {
                    //Pop up
                    Log.wtf("test","Show Dialogue");

                    showDialog(DIALOGUE_ID);
                }
            }
        });


    }

    @Override
    protected Dialog onCreateDialog(int id){
        if(id == DIALOGUE_ID)
            return new TimePickerDialog(add_events_activity.this,kTimePickerListener, hour_rem, minute_rem,true);
        else
            return null;
    }

    protected TimePickerDialog.OnTimeSetListener kTimePickerListener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    hour_rem = hourOfDay;
                    minute_rem = minute;
                }
            };


    /* Takes all the information entry and add it to the DB when button is clicked */
    private void saveEvent()
    {
        Button btnSave;
        btnSave = (Button)findViewById(R.id.saveEventBtn);
        btnSave.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.wtf("test", "Event save button clicked");

                EditText eventNameText = (EditText)findViewById(R.id.eventNameText);
                EditText descText = (EditText)findViewById(R.id.descriptionText);

                String eventName = eventNameText.getText().toString();
                String desc = descText.getText().toString();


                Toast.makeText(add_events_activity.this, "Event saved",
                        Toast.LENGTH_SHORT).show();

                Log.wtf("test", "Event name: " + eventName + " description: " + desc + " reminder: " + hour_rem + ":" + minute_rem);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_events_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
