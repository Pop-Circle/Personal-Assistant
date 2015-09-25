package pop_circle.personalassistant;


import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class calendarActivity extends AppCompatActivity {


    private boolean undo = false;
    private CaldroidFragment caldroidFragment;
    private CaldroidFragment dialogCaldroidFragment;


    private void setCustomResourceForDates() {
        Calendar cal = Calendar.getInstance();

        // Min date is last 7 days
        cal.add(Calendar.DATE, -7);
        Date blueDate = cal.getTime();

        // Max date is next 7 days
        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 7);
        Date greenDate = cal.getTime();


        if (caldroidFragment != null) {
            caldroidFragment.setBackgroundResourceForDate(R.color.caldroid_sky_blue,
                    blueDate);
            caldroidFragment.setBackgroundResourceForDate(R.color.caldroid_darker_gray,
                    greenDate);
            caldroidFragment.setTextColorForDate(R.color.caldroid_holo_blue_dark, blueDate);
            caldroidFragment.setTextColorForDate(R.color.caldroid_lighter_gray, greenDate);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar); //Change this to the activity of the home page launcher

        final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

        //Creates the calendar
        final CaldroidFragment caldroidFragment = new CaldroidFragment();
        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);

        //dunno what this does
        //setCustomResourceForDates();

        //Attach to the activity
        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar1, caldroidFragment);
        t.commit();


        Log.wtf("test", "STARTED");
        // Setup listener
        final CaldroidListener listener = new CaldroidListener() {

            @Override
            public void onSelectDate(Date date, View view) {

                /*Toast.makeText(getApplicationContext(), formatter.format(date),
                        Toast.LENGTH_SHORT).show();*/
                Intent intent = new Intent(getApplicationContext(), add_events_activity.class);
                Bundle b = new Bundle();

                //Pass the date and the month selected to the add event activity
                b.putInt("date", date.getDate());
                b.putInt("month", (date.getMonth()+1));
                intent.putExtra("dateSelected", b);

                startActivity(intent);
            }
        };

        // Setup Caldroid
        caldroidFragment.setCaldroidListener(listener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calendar, menu);
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
