package com.example.datnguyen.fitness.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.datnguyen.fitness.Adapter.RecyclerViewAdapter;
import com.example.datnguyen.fitness.Model.Exercise;
import com.example.datnguyen.fitness.R;

import java.util.ArrayList;
import java.util.List;

public class ListExercise extends AppCompatActivity {
    List<Exercise> exerciseList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exercise);
        initData();
        recyclerView = findViewById(R.id.list_ex);
        adapter = new RecyclerViewAdapter(exerciseList,getBaseContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    private void initData() {
        exerciseList.add(new Exercise(R.drawable.ex_barbell,"Barbell bench press"));
        exerciseList.add(new Exercise(R.drawable.ex_dips,"Dips"));
        exerciseList.add(new Exercise(R.drawable.ex_pushup,"Push up"));
        exerciseList.add(new Exercise(R.drawable.ex_biceps,"Biceps"));
        exerciseList.add(new Exercise(R.drawable.ex_deadlift,"Deadlift"));
        exerciseList.add(new Exercise(R.drawable.ex_pullup,"Pull up"));
        exerciseList.add(new Exercise(R.drawable.ex_bouldershoulder,"Boulder shoulders"));
        exerciseList.add(new Exercise(R.drawable.ex_lateralshoulderraises,"Lateral shoulder raises"));
        exerciseList.add(new Exercise(R.drawable.ex_dumbellshoulder,"Dumbell shoulders"));
        exerciseList.add(new Exercise(R.drawable.ex_dumbelldeadlift,"Dumbell deadlift"));

    }
}
