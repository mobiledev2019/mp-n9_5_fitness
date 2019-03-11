package com.example.datnguyen.fitness.Others;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.datnguyen.fitness.R;

public class AlarmNotificationReceiver extends BroadcastReceiver {
    private NotificationManagerCompat notificationManager;
    @Override
    public void onReceive(Context context, Intent intent) {

        notificationManager = NotificationManagerCompat.from(context);
        Notification notification = new NotificationCompat.Builder(context,NotificationChannel.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_alarm_on_black_24dp)
                .setContentTitle("Dậy đê")
                .setContentText("Đến giờ tập rồi")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .build();
        notificationManager.notify(1,notification);


    }

}
