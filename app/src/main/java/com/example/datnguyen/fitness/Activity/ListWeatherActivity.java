package com.example.datnguyen.fitness.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.datnguyen.fitness.R;

public class ListWeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_weather);
        Intent intent = getIntent();
        Toast.makeText(this, intent.getStringExtra("city"), Toast.LENGTH_SHORT).show();
    }
}
