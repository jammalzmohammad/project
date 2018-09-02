package justparking.justparking;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class Notification_receiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager=(NotificationManager)
                context.getSystemService(context.NOTIFICATION_SERVICE);

        Intent repeating_intent=new Intent(context,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,100,
                repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(R.drawable.smartparking)
                .setContentText("your Booking time is done please cancel the booking ")
                .setContentTitle("Booking")
                .setAutoCancel(true);

        notificationManager.notify(100,builder.build());
    }
}
