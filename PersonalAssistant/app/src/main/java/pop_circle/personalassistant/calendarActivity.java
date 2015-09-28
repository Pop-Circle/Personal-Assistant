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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class calendarActivity extends AppCompatActivity {


    private boolean undo = false;
    private CaldroidFragment caldroidFragment;
    private CaldroidFragment dialogCaldroidFragment;
    private PaDbHelper db;
    private final int YEAR = 2015; //This is hardcoded



    private void colourEventDates() {
        List<String> listDates = db.getAllEventDates();
        Set<String> tempDates = new HashSet<>();
        List<Integer> day = new ArrayList<Integer>();
        List<Integer> month = new ArrayList<Integer>();

        //Takes out all the duplicates
        tempDates.addAll(listDates);
        listDates.clear();
        listDates.addAll(tempDates);


        Calendar cal = Calendar.getInstance();
        cal = Calendar.getInstance();
        Date highlightedDates = new Date();

        for (int i = 0; i < listDates.size(); i++)
        {
            String[] parts = listDates.get(i).split("-");
            String sday = parts[0];
            String smonth = parts[1];

            day.add(Integer.parseInt(sday.trim()));

            switch (smonth.trim())
            {
                case "January":
                    month.add(0);
                    break;
                case "February":
                    month.add(1);
                    break;
                case "March":
                    month.add(2);
                    break;
                case "April":
                    month.add(3);
                    break;
                case "May":
                    month.add(4);
                    break;
                case "June":
                    month.add(5);
                    break;
                case "July":
                    month.add(6);
                    break;
                case "August":
                    month.add(7);
                    break;
                case "September":
                    month.add(8);
                    break;
                case "October":
                    month.add(9);
                    break;
                case "November":
                    month.add(10);
                    break;
                case "December":
                    month.add(11);
                    break;
            }

            //Colouring in the dates
            cal.set(YEAR, month.get(i), day.get(i));
            highlightedDates = cal.getTime();
            caldroidFragment.setTextColorForDate(R.color.caldroid_light_red,
                    highlightedDates);

        }




    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar); //Change this to the activity of the home page launcher

        db = new PaDbHelper(this);

        final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");

        //Creates the calendar
        caldroidFragment = new CaldroidFragment();
        //final CaldroidFragment caldroidFragment = new CaldroidFragment();

        Bundle args = new Bundle();
        Calendar cal = Calendar.getInstance();
        args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
        args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
        caldroidFragment.setArguments(args);

        //dunno what this does
        colourEventDates();

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
