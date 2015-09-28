package pop_circle.personalassistant;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.DatabaseMetaData;
import java.util.Calendar;
import java.util.List;

public class edit_events extends AppCompatActivity {

    private ArrayAdapter<String> mAdapter;
    private ListView mDrawerList;

    static final int DIALOGUE_ID = 0;
    int hour_rem; //for reminder
    int minute_rem; //for reminder
    int hour_time; //Event time
    int minute_time; //Event time
    String eventName;
    String desc;
    boolean checked = false;
    int dateSelected;
    String dateLine;
    String monthSelected;
    PaDbHelper db;

    String ownerID; // Need to recieve the owner ID to know who youre editing
    int agendaID;
    int amountofEvent;
    Event currevent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.wtf("test", "Entered edit screen");

        super.onCreate(savedInstanceState);


        db = new PaDbHelper(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("editAgenda");
        agendaID = bundle.getInt("agendaID");
        amountofEvent = bundle.getInt("amountEvent");
        ownerID = bundle.getString("ownerID");
        dateLine = bundle.getString("dateLine");

        setContentView(R.layout.activity_edit_events);


        currevent = findEvent(agendaID);

        //Show the month and date on the event page
        TextView dateMonthLabel = (TextView) findViewById(R.id.monthYear);
        dateMonthLabel.setText(dateLine);


        setDetails();

        openAgenda();
        saveEvent();
        eventAmount();
        timePickerDialogue();
    }


    private Event findEvent(int _eventID)
    {
        List<Event> listEvent = db.getAllEvents(dateLine, ownerID);
        Event event = null;
        for (int i = 0; i < amountofEvent; i++) {
            int eID = listEvent.get(i).getID();
            if(_eventID == eID)
            {
                return listEvent.get(i);
            }
        }
        return event;
    }

    private void setDetails()
    {
        //Event Name
        EditText eventNameText = (EditText)findViewById(R.id.eventNameText);
        eventNameText.setText(currevent.getEventName());

        //Event Desc
        EditText descriptionText = (EditText)findViewById(R.id.descriptionText);
        descriptionText.setText(currevent.getEventDesc());

        //Event Time
        String etime = currevent.getEventTime();
        String[] parts = etime.split(":");
        int etimeHour = Integer.parseInt(parts[0].trim());
        int etimeMin = Integer.parseInt(parts[1].trim());

        TimePicker eventTime = (TimePicker)findViewById(R.id.eventTime);
        eventTime.setCurrentHour(etimeHour);
        eventTime.setCurrentMinute(etimeMin);

        //Event Reminder
        CheckBox remCheck = (CheckBox)findViewById(R.id.reminderCheckBox);
        String erem = currevent.getEventRem();
        String[] partsrem = erem.split(":");
        int eremHour = Integer.parseInt(partsrem[0].trim());
        int eremMin = Integer.parseInt(partsrem[1].trim());

        //Reminder is not checked
        if(eremHour == -1 || eremMin == -1)
        {
            remCheck.setChecked(false);
        }
        else
        {
            remCheck.setChecked(true);
            hour_rem = eremHour;
            minute_rem = eremMin;
        }

    }

    /* Show the amount of events on this day */
    private void eventAmount(){
        int countEvent = db.getEventCount(dateLine,ownerID);
        TextView eventCountLabel = (TextView)findViewById(R.id.eventAmount);
        eventCountLabel.setText("Total: " + countEvent);
    }

    private void openAgenda(){
        ImageButton agendaBtn = (ImageButton)findViewById(R.id.agendaButton);
        agendaBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), agenda.class);
                Bundle b = new Bundle();

                //Pass the date and the month selected to the add event activity
                b.putString("dateLine", dateLine);
                b.putString("ownerID", ownerID);
                intent.putExtra("CurrentDayAgenda", b);

                startActivity(intent);
            }
        });
    }

    /* Time picker pop up for reminder. Only shows when you check reminder */
    private void timePickerDialogue()
    {
        final CheckBox reminderCB = (CheckBox)findViewById(R.id.reminderCheckBox);
        reminderCB.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                checked = ((CheckBox) v).isChecked();
                Log.wtf("test", "checkbox Clicking " + checked ) ;
                if(checked) {
                    //Pop up
                    showDialog(DIALOGUE_ID);
                }
            }
        });


    }

    /* For time picker dialog */
    @Override
    protected Dialog onCreateDialog(int id){
        if(id == DIALOGUE_ID)
            return new TimePickerDialog(edit_events.this,kTimePickerListener, hour_rem, minute_rem,true);
        else
            return null;
    }

    /* Gets the time from the reminder  */
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


                EditText eventNameText = (EditText)findViewById(R.id.eventNameText);
                EditText descText = (EditText)findViewById(R.id.descriptionText);

                eventName = eventNameText.getText().toString();
                desc = descText.getText().toString();

                TimePicker eventTimeP = (TimePicker)findViewById(R.id.eventTime);
                hour_time = eventTimeP.getCurrentHour();
                minute_time = eventTimeP.getCurrentMinute();

                String eventTime = String.valueOf(hour_time) +":" + String.valueOf(minute_time);
                String eventRem = String.valueOf(hour_rem)+":"+String.valueOf(minute_rem);

                Log.wtf("test","EVENT ID " + eventNameText.getId());


                currevent.setEventName(eventName);
                currevent.setEventDesc(desc);
                currevent.setEventRem(eventRem);
                currevent.setEventTime(eventTime);


                int update = db.updateEvent(currevent);
                Log.wtf("test", " UPDATE : " + update);

                Toast.makeText(edit_events.this, "Event updated",
                        Toast.LENGTH_SHORT).show();


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
