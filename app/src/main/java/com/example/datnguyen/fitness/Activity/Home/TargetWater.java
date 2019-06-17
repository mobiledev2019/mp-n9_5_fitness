package com.example.datnguyen.fitness.Activity.Home;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.example.datnguyen.fitness.R;

public class TargetWater extends AppCompatActivity {
    private Switch switchWater;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_water);

        //Animation
        overridePendingTransition(R.anim.fade_in,R.anim.zoom_out);

        switchWater = findViewById(R.id.switchWater);

        //get switch state
        sharedPreferences = getSharedPreferences("dailyWater",MODE_PRIVATE);
        switchWater.setChecked(sharedPreferences.getBoolean("state",false));

        //set Switch Text
        if (switchWater.isChecked()){
            switchWater.setText("On");
        }
        else {
            switchWater.setText("Off");
        }

        switchWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (switchWater.isChecked())
                {
                    editor.putBoolean("state",true);
                    switchWater.setText("On");
                }
                else {
                    editor.putBoolean("state",false);
                    switchWater.setText("Off");
                }
                editor.commit();
            }
        });
    }


}
