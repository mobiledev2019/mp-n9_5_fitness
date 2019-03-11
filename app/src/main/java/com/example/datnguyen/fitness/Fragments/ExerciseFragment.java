package com.example.datnguyen.fitness.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.datnguyen.fitness.Activity.Calendar;
import com.example.datnguyen.fitness.Activity.CardioTraining;
import com.example.datnguyen.fitness.Activity.ListExercise;
import com.example.datnguyen.fitness.R;
import com.example.datnguyen.fitness.Activity.SettingPage;

public class ExerciseFragment extends Fragment {
    private Button btnStartOnline, btnStartExercise, btnStartCardio;
    private Button btnSettingExercise, btnSettingCardio;
    private Button btnCalendar;
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_exercise,container,false);
        init();
        handleEvent();
        return view;
    }

    private void init() {
        btnCalendar = view.findViewById(R.id.btnCalendarCardio);

        btnSettingCardio = view.findViewById(R.id.btnSettingCardio);
        btnSettingExercise= view.findViewById(R.id.btnSettingExercise);

        btnStartOnline = view.findViewById(R.id.btnStartOnline);
        btnStartExercise = view.findViewById(R.id.btnStartExercise);
        btnStartCardio = view.findViewById(R.id.btnStartCardio);
    }

    private void handleEvent(){
         btnStartExercise.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getContext(),ListExercise.class);
                 startActivity(intent);
             }
         });

         btnStartCardio.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getContext(),CardioTraining.class);
                 startActivity(intent);
             }
         });

         btnSettingExercise.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getContext(),SettingPage.class);
                 startActivity(intent);
             }
         });

        btnSettingCardio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),SettingPage.class);
                startActivity(intent);
            }
        });

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),Calendar.class);
                startActivity(intent);
            }
        });

    }
}
