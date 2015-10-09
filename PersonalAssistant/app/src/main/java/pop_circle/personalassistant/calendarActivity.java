package pop_circle.personalassistant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pop_circle.personalassistant.Location;
import pop_circle.personalassistant.Weather;

import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
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
import java.util.Locale;
import java.util.Random;
import java.util.Set;

//weather detail: http://api.openweathermap.org/data/2.5/weather?q=pretoria,za&APPID=fe9a5e6dbbd048f1ca269574188f7ab4
public class calendarActivity extends AppCompatActivity {


    private boolean undo = false;
    private CaldroidFragment caldroidFragment;
    private CaldroidFragment dialogCaldroidFragment;
    private PaDbHelper db;
    private final int YEAR = 2015; //This is hardcoded
    private PendingIntent pendingIntent;
    int user;
    private TextView condDescr;
    private ImageView imgView;
    private TextView temp;
    private TextView cityText;
    GPStrack gps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar); //Change this to the activity of the home page launcher
        String city = ""; // default, should overwrite later

        user= ((MyApplication) this.getApplication()).getLoggedUser();
        condDescr = (TextView) findViewById(R.id.condition);
        temp = (TextView) findViewById(R.id.temp);
        cityText = (TextView) findViewById(R.id.locationText);
        imgView = (ImageView) findViewById(R.id.imgWeather);
        gps = new GPStrack(calendarActivity.this);
        if(gps.canGetLocation()) {
            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            Geocoder gcd = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = null;
            try {
                addresses = gcd.getFromLocation(latitude, longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (addresses.size() > 0)
               /* Toast.makeText(
                        getApplicationContext(),
                        "Your Location is -\nLat: " + latitude + "\nLong: "
                                + longitude, Toast.LENGTH_LONG).show();*/
                city = addresses.get(0).getLocality() + "," + addresses.get(0).getCountryCode();
                cityText.setText( addresses.get(0).getLocality() + ", " + addresses.get(0).getCountryName());
        } else {
            gps.showSettingsAlert();
        }

        db = new PaDbHelper(this);
        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[]{city});
        gps = new GPStrack(calendarActivity.this);


    }

    private class JSONWeatherTask extends AsyncTask<String, Void, Weather> {

        @Override
        protected Weather doInBackground(String... params) {
            Weather weather = new Weather();
            String data = ((new WeatherHttpClient()).getWeatherData(params[0]));

            try {
                weather = JsonWeatherParser.getWeather(data);

                // Let's retrieve the icon
                weather.iconData = ((new WeatherHttpClient()).getImage(weather.currentCondition.getIcon()));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weather;

        }

        @Override
        protected void onPostExecute(Weather weather) {
            super.onPostExecute(weather);

            if (weather.iconData != null && weather.iconData.length > 0) {
                Bitmap img = BitmapFactory.decodeByteArray(weather.iconData, 0, weather.iconData.length);
                imgView.setImageBitmap(img);
            }
            else
            {
                System.out.print("Na fam no Pictures heree------------------------------");
            }
            //cityText.setText(weather.location.getCity() + "," + weather.location.getCountry());
            condDescr.setText("Current Condition: " + weather.currentCondition.getCondition() + "(" + weather.currentCondition.getDescr() + ")");
            temp.setText("Current Temperature: " + "" + Math.round((weather.temperature.getTemp() - 273.15)) + "°C");

        }
    }

    /* Sets the alarm for the current day */
    private void setAlarm()
    {
        List<Event> listDates = db.getEventEverything();
        List<Event> today = new ArrayList<Event>();
        List<String> dates = new ArrayList<String>();
        Calendar c = Calendar.getInstance();
        String datet = String.valueOf(c.get(Calendar.DATE));
        int montht = c.get(Calendar.MONTH);

        String monthLine = "";
        switch(montht)
        {
            case 0:
                monthLine = "January";
                break;
            case 1:
                monthLine = "February";
                break;
            case 2:
                monthLine = "March";
                break;
            case 3:
                monthLine = "April";
                break;
            case 4:
                monthLine = "May";
                break;
            case 5:
                monthLine = "June";
                break;
            case 6:
                monthLine = "July";
                break;
            case 7:
                monthLine = "August";
                break;
            case 8:
                monthLine = "September";
                break;
            case 9:
                monthLine = "October";
                break;
            case 10:
                monthLine = "November";
                break;
            case 11:
                monthLine = "December";
                break;
        }

        for(int i=0;i<listDates.size();i++){
            String dateLine = listDates.get(i).getEventDate();
            String[] parts = dateLine.split("-");
            String day = parts[0].trim();
            String month = parts[1].trim();

            if(datet.equals(day) && month.equals(monthLine))
            {
                //Today
                if(!listDates.get(i).getEventRem().equals("-1:-1"))
                    today.add(listDates.get(i));
            }
        }


        for(int i=0;i<today.size();i++)
        {
            Log.wtf("test","today : " + today.get(i).getEventName());

            String todayRem = today.get(i).getEventRem();
            String[] todayParts = todayRem.split(":");
            String todayHour = todayParts[0].trim();
            String todayMin = todayParts[1].trim();


        }
        createAlarm(today);
    }

    private void createAlarm(List<Event> event)
    {

        AlarmManager manager = (AlarmManager)getSystemService(this.ALARM_SERVICE);
        ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();
        String todayRem;
        String[] todayParts;
        int todayHour;
        int todayMin;
        Calendar calendar;

        for(int i=0;i<event.size();i++)
        {
            Intent alarmIntent = new Intent(this, eventAlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this, event.get(i).getID(), alarmIntent, 0);

            manager = (AlarmManager)getSystemService(this.ALARM_SERVICE);
            calendar = Calendar.getInstance();

            todayRem = event.get(i).getEventRem();
            todayParts = todayRem.split(":");
            todayHour = Integer.parseInt(todayParts[0].trim());
            todayMin = Integer.parseInt(todayParts[1].trim());

            if(todayHour == -1 || todayMin==-1)
            {
                manager.cancel(intentArray.get(event.get(i).getID()));
                Log.wtf("test", "Rem removed : " + event.get(i).getEventName());
            }
            else {
                calendar.set(Calendar.HOUR_OF_DAY, todayHour);
                calendar.set(Calendar.MINUTE, todayMin);
                calendar.set(Calendar.SECOND, 0);


                //manager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), calendar.getTimeInMillis(), pendingIntent);
                intentArray.add(pendingIntent);


                Log.wtf("test", "Rem added : " + event.get(i).getEventName() + " time " + calendar.getTime());
            }

        }

        /*
        PendingIntent pendingIntent;
        AlarmManager manager;
        Intent alarmIntent = new Intent(this, eventAlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);

        manager = (AlarmManager)getSystemService(this.ALARM_SERVICE);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        Log.wtf("test", " asd " + calendar.getTime().toString());

        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), calendar.getTimeInMillis(), pendingIntent);
*/
    }


    private void colourEventDates() {
        caldroidFragment.refreshView();

        List<String> listDates = db.getAllEventDates(user);

        Log.wtf("test","userCal " + user);

        for(int i =0;i<listDates.size();i++)
            Log.wtf("test","===== Even " + listDates.get(i).toString());


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
        caldroidFragment.refreshView();
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

    @Override
    public void onResume() {
        super.onResume();


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
                Intent intent = new Intent(getApplicationContext(), add_events_activity.class);
                Bundle b = new Bundle();

                //Pass the date and the month selected to the add event activity
                b.putInt("date", date.getDate());
                b.putInt("month", (date.getMonth()+1));
                intent.putExtra("dateSelected", b);
                startActivity(intent);
            }

            @Override
            public void onCaldroidViewCreated() {
                caldroidFragment.refreshView();
            }
        };

        // Setup Caldroid
        caldroidFragment.setCaldroidListener(listener);

       // setAlarm();
    }
}
