package pop_circle.personalassistant;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Jackie on 2015-09-29.
 */
public class eventAlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {



        Log.wtf("test","---Alarm--");
        showNotification(context);

/*
        Intent toLaunch = new Intent(context, calendarActivity.class);
        toLaunch.addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        toLaunch.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Random r = new Random();
        int notifId = r.nextInt();
        PendingIntent intentBack = PendingIntent.getActivity(context, notifId, toLaunch, 0);


        long id = intent.getLongExtra("id", 0);
        Notification n = new Notification(R.drawable.ic_drawer, "Reminder",
                System.currentTimeMillis());

        NotificationManager nm = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify((int)id, n);
*/
    }

    private void showNotification(Context context) {
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, calendarActivity.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_drawer)
                        .setContentTitle("Personal Assistant")
                        .setContentText("Event reminder");
        mBuilder.setContentIntent(contentIntent);
        mBuilder.setDefaults(Notification.DEFAULT_SOUND);
        mBuilder.setAutoCancel(true);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());

    }

}
