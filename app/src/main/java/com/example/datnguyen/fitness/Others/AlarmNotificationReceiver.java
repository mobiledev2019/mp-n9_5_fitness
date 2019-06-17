package com.example.datnguyen.fitness.Others;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.datnguyen.fitness.Activity.Exercises.ListExercise;
import com.example.datnguyen.fitness.R;

import java.util.ArrayList;
import java.util.Collections;

public class AlarmNotificationReceiver extends BroadcastReceiver {
    private NotificationManagerCompat notificationManager;
    private ArrayList<String> exercises;
    @Override
    public void onReceive(Context context, Intent intent) {

        addExercises();
        Collections.shuffle(exercises);

        Intent notificationIntent = new Intent(context, ListExercise.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        notificationManager = NotificationManagerCompat.from(context);
        Notification notification = new NotificationCompat.Builder(context,NotificationChannel.CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_alarm_on_black_24dp)
                .setContentTitle("Today Exercise")
                .setContentText("You should do "+exercises.get(0)+" today!")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setContentIntent(pendingIntent)
                .build();
        notificationManager.notify(1,notification);


    }

    private void addExercises() {
        exercises = new ArrayList<>();
        exercises.add("Barbell bench press");
        exercises.add("Dips");
        exercises.add("Push up");
        exercises.add("Biceps");
        exercises.add("Deadlift");
        exercises.add("Pull up");
        exercises.add("Boulder shoulders");
        exercises.add("Lateral shoulder raises");
        exercises.add("Dumbell shoulders");
        exercises.add("Dumbell deadlift");

    }


}
