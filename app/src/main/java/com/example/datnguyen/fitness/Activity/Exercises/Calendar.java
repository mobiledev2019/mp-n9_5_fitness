package com.example.datnguyen.fitness.Activity.Exercises;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.datnguyen.fitness.Database.WorkoutDB;
import com.example.datnguyen.fitness.Others.WorkoutDoneDecorator;
import com.example.datnguyen.fitness.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class Calendar extends AppCompatActivity {
    MaterialCalendarView materialCalendarView;
 //   HashSet<CalendarDay> list = new HashSet<>();

    WorkoutDB workoutDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        //Animation
        overridePendingTransition(R.anim.fade_in,R.anim.zoom_out);


        workoutDB = new WorkoutDB(this);

        materialCalendarView = findViewById(R.id.calendar);

        // get workout day list from db
        List<String> workoutDay = workoutDB.getWorkoutDays();
        HashSet<CalendarDay> convertedList = new HashSet<>();
        for(String value:workoutDay)
            convertedList.add(CalendarDay.from(new Date(Long.parseLong(value))));
        materialCalendarView.addDecorator(new WorkoutDoneDecorator(convertedList));


    }
}
