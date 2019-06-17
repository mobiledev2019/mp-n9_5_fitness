package com.example.datnguyen.fitness.Activity.Statistics;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.datnguyen.fitness.Database.CaloriesDB;
import com.example.datnguyen.fitness.Model.Calories;
import com.example.datnguyen.fitness.R;

import java.util.List;

public class Statistics extends AppCompatActivity {
    private CaloriesDB caloriesDB;
    private List<Calories> caloriesList;
    private Button btnWater, btnFood, btnExercise;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_statistics);

        caloriesDB = new CaloriesDB(this);
        caloriesList = caloriesDB.getAllCalories();

        btnExercise = findViewById(R.id.btnExSta);
        btnFood = findViewById(R.id.btnFoodSta);
        btnWater = findViewById(R.id.btnWaterSta);

        btnWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Statistics.this,ViewStatistics.class);
                intent.putExtra("id","water");
                startActivity(intent);
            }
        });

        btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Statistics.this,ViewStatistics.class);
                intent.putExtra("id","food");
                startActivity(intent);
            }
        });

        btnExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Statistics.this,ViewStatistics.class);
                intent.putExtra("id","exercise");
                startActivity(intent);;
            }
        });

    }
}
