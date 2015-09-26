package pop_circle.personalassistant;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Stashie on 2015/09/23.
 */

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Personalize extends AppCompatActivity {

    private Switch sleepSwitch;
    private TimePicker tSleep, tWake;
    private TextView t;
    private Button save;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personalize_device);
        sleepSwitch = (Switch) findViewById(R.id.sleepToggle);
        tSleep = (TimePicker) findViewById(R.id.sleepTime);
        tWake = (TimePicker) findViewById(R.id.wakeTime);
        save = (Button) findViewById(R.id.saveBut);
        t = (TextView) findViewById(R.id.wakeText);
            sleepSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {
                    if (isChecked) {
                        tSleep.setVisibility(View.VISIBLE);
                        tWake.setVisibility(View.VISIBLE);
                        t.setVisibility(View.VISIBLE);
                    } else {
                        tSleep.setVisibility(View.INVISIBLE);
                        tWake.setVisibility(View.INVISIBLE);
                        t.setVisibility(View.INVISIBLE);
                    }
                }
            });
        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (sleepSwitch.isChecked()) {
                    CharSequence text = "Sleep Schedule Activated" ;
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    Calendar sleepSet = Calendar.getInstance();
                    sleepSet.set(Calendar.HOUR_OF_DAY, tSleep.getCurrentHour());
                    sleepSet.set(Calendar.MINUTE, tSleep.getCurrentMinute());
                    sleepSet.set(Calendar.SECOND, 0);
                    String alarm = ALARM_SERVICE;
                    Intent receiverSilentIntent = new Intent(context, silent.class);
                    AlarmManager am = (AlarmManager) Personalize.this.getSystemService(alarm);
//create a pending intent to be called at sleep time
                    PendingIntent sleepPI = PendingIntent.getBroadcast(context, 123456789, receiverSilentIntent, 0);
                    //PendingIntent sleepPI = PendingIntent.getService(Personalize.this, 0, new Intent("imy320.personalassistant.silent"), PendingIntent.FLAG_UPDATE_CURRENT);
//schedule time for pending intent, and set the interval to day so that this event will repeat at the selected time every day
                    am.setRepeating(AlarmManager.RTC_WAKEUP, sleepSet.getTimeInMillis(), AlarmManager.INTERVAL_DAY, sleepPI);

                    Calendar wakeSet = Calendar.getInstance();
//set the time to Wake up
                    Intent receiverWakeIntent = new Intent(context, unSilence.class);
                    wakeSet.set(Calendar.HOUR_OF_DAY, tWake.getCurrentHour());
                    wakeSet.set(Calendar.MINUTE, tWake.getCurrentMinute());
                    wakeSet.set(Calendar.SECOND, 0);
//create a pending intent to be called at wake time
                    PendingIntent wakePI = PendingIntent.getBroadcast(context, 12345678, receiverWakeIntent, 0);
                    //PendingIntent wakePI = PendingIntent.getService(Personalize.this, 0, new Intent("imy320.personalassistant.unSilence"), PendingIntent.FLAG_UPDATE_CURRENT);
//schedule time for pending intent, and set the interval to day so that this event will repeat at the selected time every day
                    am.setRepeating(AlarmManager.RTC_WAKEUP, wakeSet.getTimeInMillis(), AlarmManager.INTERVAL_DAY, wakePI);

                }
                else
                {
                    CharSequence text = "Sleep Schedule Deactivated" ;
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    Intent receiverSilentIntent = new Intent(context, silent.class);
                    //get Alarm manager instance
                    String alarm = Context.ALARM_SERVICE;
                    AlarmManager am = (AlarmManager) Personalize.this.getSystemService(alarm);
                    PendingIntent sleepPI = PendingIntent.getBroadcast(context, 12345678, receiverSilentIntent, 0);
//cancel it
                    am.cancel(sleepPI);
//build intent for wake up
                    Intent receiverWakeIntent = new Intent(context, unSilence.class);
                    PendingIntent wakePI = PendingIntent.getBroadcast(context, 12345678, receiverWakeIntent, 0);
//cancel it
                    am.cancel(wakePI);
                }
            }
        });
    }






}
