package com.example.datnguyen.fitness.Activity.Exercises;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.datnguyen.fitness.Adapter.ExerciseAdapter;
import com.example.datnguyen.fitness.Database.ExerciseDB;
import com.example.datnguyen.fitness.Model.TempExercise;
import com.example.datnguyen.fitness.R;

import java.util.ArrayList;

public class ExerciseSelection extends AppCompatActivity {
    ListView lvSelection;
    ExerciseAdapter adapter;
    ArrayList<TempExercise> exercises;
    ExerciseDB exerciseDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_selection);

        //Animation
        overridePendingTransition(R.anim.fade_in,R.anim.zoom_out);

        exerciseDB = new ExerciseDB(this);
        init();

    }

    private void init() {
        lvSelection = findViewById(R.id.listSelectedEx);
        exercises = exerciseDB.getAllTempExercises();
        adapter = new ExerciseAdapter(this,R.layout.item_selection,exercises);
        lvSelection.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.move_on)
        {
            Intent intent = new Intent(ExerciseSelection.this, ListExercise.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
