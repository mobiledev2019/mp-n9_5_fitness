package com.example.datnguyen.fitness.Activity.Exercises;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.datnguyen.fitness.Adapter.RecyclerViewAdapter;
import com.example.datnguyen.fitness.Database.ExerciseDB;
import com.example.datnguyen.fitness.Model.Exercise;
import com.example.datnguyen.fitness.Model.TempExercise;
import com.example.datnguyen.fitness.Others.CaloriesCaculator;
import com.example.datnguyen.fitness.R;

import java.util.ArrayList;
import java.util.List;

public class ListExercise extends AppCompatActivity {
    List<Exercise> exerciseList = new ArrayList<>();
    ArrayList<TempExercise> tempExercises;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    ExerciseDB exerciseDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exercise);

        //Animation
        overridePendingTransition(R.anim.fade_in,R.anim.zoom_out);

        exerciseDB = new ExerciseDB(this);
        initData();
        recyclerView = findViewById(R.id.list_ex);
        adapter = new RecyclerViewAdapter(exerciseList,getBaseContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    private void initData() {
        tempExercises = exerciseDB.getAllTempExercises();
        for (int i = 0 ; i<tempExercises.size(); i++)
        {
            if (tempExercises.get(i).getId() == 1 && tempExercises.get(i).isCheck() == 1)
            {
                exerciseList.add(new Exercise(R.drawable.ex_dips,CaloriesCaculator.DIPS));
            }
            if (tempExercises.get(i).getId() == 2 && tempExercises.get(i).isCheck() == 1)
            {
                exerciseList.add(new Exercise(R.drawable.ex_pushup,CaloriesCaculator.PUSH_UP));
            }
            if (tempExercises.get(i).getId() == 3 && tempExercises.get(i).isCheck() == 1)
            {
                exerciseList.add(new Exercise(R.drawable.ex_biceps,CaloriesCaculator.BICEPS));
            }
            if (tempExercises.get(i).getId() == 4 && tempExercises.get(i).isCheck() == 1)
            {
                exerciseList.add(new Exercise(R.drawable.ex_deadlift,CaloriesCaculator.DEADLIFT));
            }
            if (tempExercises.get(i).getId() == 5 && tempExercises.get(i).isCheck() == 1)
            {
                exerciseList.add(new Exercise(R.drawable.ex_pullup,CaloriesCaculator.PULL_UP));
            }
            if (tempExercises.get(i).getId() == 6 && tempExercises.get(i).isCheck() == 1)
            {
                exerciseList.add(new Exercise(R.drawable.ex_bouldershoulder,CaloriesCaculator.BOULDER_SHOULDERS));
            }
            if (tempExercises.get(i).getId() == 7 && tempExercises.get(i).isCheck() == 1)
            {
                exerciseList.add(new Exercise(R.drawable.ex_lateralshoulderraises,CaloriesCaculator.LATERAL_SHOULDER_RAISES));
            }
            if (tempExercises.get(i).getId() == 8 && tempExercises.get(i).isCheck() == 1)
            {
                exerciseList.add(new Exercise(R.drawable.ex_dumbellshoulder,CaloriesCaculator.DUMBELL_SHOULDERS));
            }
            if (tempExercises.get(i).getId() == 9 && tempExercises.get(i).isCheck() == 1)
            {
                exerciseList.add(new Exercise(R.drawable.ex_dumbelldeadlift,CaloriesCaculator.DUMBELL_DEADLIFT));
            }
            if (tempExercises.get(i).getId() == 10 && tempExercises.get(i).isCheck() == 1)
            {
                exerciseList.add(new Exercise(R.drawable.ex_barbell,CaloriesCaculator.BARBELL_BENCH_PRESS));
            }
        }


    }
}
