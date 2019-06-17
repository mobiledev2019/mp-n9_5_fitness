package com.example.datnguyen.fitness.Activity.Exercises;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.datnguyen.fitness.Database.CaloriesDB;
import com.example.datnguyen.fitness.Database.WorkoutDB;
import com.example.datnguyen.fitness.Model.Calories;
import com.example.datnguyen.fitness.Others.CaloriesCaculator;
import com.example.datnguyen.fitness.Others.Common;
import com.example.datnguyen.fitness.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ViewExercise extends AppCompatActivity {

    int image_id;
    String name;
    TextView timer, title;
    ImageView detail_image, img_sound;
    Button btnStart;
    // check btnStart's state
    boolean isRunning = false;
    int calories;
    MediaPlayer mediaPlayer;
    WorkoutDB workoutDB;
    CaloriesDB caloriesDB;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercise);

        //Animation
        overridePendingTransition(R.anim.bounce,R.anim.zoom_out);

        workoutDB = new WorkoutDB(this);
        caloriesDB = new CaloriesDB(this);

        timer = findViewById(R.id.timer);
        title = findViewById(R.id.titleEx);
        detail_image = findViewById(R.id.detail_image);
        img_sound = findViewById(R.id.img_sound);

        btnStart = findViewById(R.id.btnStart);

        // get intent from ListExercise
        if (getIntent() != null)
        {
            image_id = getIntent().getIntExtra("image_id",-1);
            name = getIntent().getStringExtra("name");

            setCaloriesInfo();
            Toast.makeText(this, ""+calories, Toast.LENGTH_SHORT).show();

            //  detail_image.setImageResource(image_id);
            Glide.with(this).load(image_id).into(detail_image);
            title.setText(name);

        }
        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Timer
                if (!isRunning)
                {
                    mediaPlayer = MediaPlayer.create(ViewExercise.this,R.raw.boom);
                    mediaPlayer.start();


                    //image_sound
                    img_sound.setVisibility(View.VISIBLE);
                    img_sound.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mediaPlayer.isPlaying())
                            {
                                img_sound.setImageResource(R.drawable.sound_off);
                                mediaPlayer.pause();
                            }
                            else {
                                img_sound.setImageResource(R.drawable.soud_on);
                                mediaPlayer.start();
                            }
                        }
                    });
                    btnStart.setText("DONE");

                    int timeLimit = 0;
                    // set TimeLimit
                    if (workoutDB.getSettingMode() == 0)
                    {
                        timeLimit = Common.TIME_LIMIT_EASY;
                        calories = calories * 1;
                    }
                    else if (workoutDB.getSettingMode() == 1)
                    {
                        timeLimit = Common.TIME_LIMIT_MEDIUM;
                        calories = calories * 2;
                    }
                    else if (workoutDB.getSettingMode() == 2)
                    {
                        timeLimit = Common.TIME_LIMIT_HARD;
                        calories = calories * 3;
                    }

                  countDownTimer =  new CountDownTimer(timeLimit,1000){
                        @Override
                        public void onTick(long millisUntilFinished) {
                            timer.setText(""+millisUntilFinished/1000);
                        }

                        @Override
                        public void onFinish() {
                            Calendar calendar = Calendar.getInstance();
                            SimpleDateFormat mdformat = new SimpleDateFormat("dd / MM / yyyy ");
                            String strDate = mdformat.format(calendar.getTime());
                            Calories calo = new Calories(""+strDate,calories);
                            caloriesDB.saveCalories(calo);
                            Toast.makeText(ViewExercise.this, "Finish!!", Toast.LENGTH_SHORT).show();
                            finish();
                            mediaPlayer.stop();
                        }
                    }.start();
                }
                else {
                    Toast.makeText(ViewExercise.this, "Finish!!", Toast.LENGTH_SHORT).show();
                    finish();
                    mediaPlayer.stop();

                }
                isRunning = !isRunning;

            }
        });

        timer.setText("");

    }

    private void setCaloriesInfo() {
        if (name.equals(CaloriesCaculator.DIPS))
        {
            calories = CaloriesCaculator.CAL_DIPS;
        }

        if (name.equals(CaloriesCaculator.PUSH_UP))
        {
            calories = CaloriesCaculator.CAL_PUSH_UP;
        }

        if (name.equals(CaloriesCaculator.BICEPS))
        {
            calories = CaloriesCaculator.CAL_BICEPS;
        }

        if (name.equals(CaloriesCaculator.DEADLIFT))
        {
            calories = CaloriesCaculator.CAL_DEADLIFT;
        }

        if (name.equals(CaloriesCaculator.PULL_UP))
        {
            calories = CaloriesCaculator.CAL_PULL_UP;
        }

        if (name.equals(CaloriesCaculator.BOULDER_SHOULDERS))
        {
            calories = CaloriesCaculator.CAL_BOULDER_SHOULDERS;
        }

        if (name.equals(CaloriesCaculator.LATERAL_SHOULDER_RAISES))
        {
            calories = CaloriesCaculator.CAL_LATERAL_SHOULDER_RAISES;
        }

        if (name.equals(CaloriesCaculator.DUMBELL_SHOULDERS))
        {
            calories = CaloriesCaculator.CAL_DUMBELL_SHOULDERS;
        }

        if (name.equals(CaloriesCaculator.DUMBELL_DEADLIFT))
        {
            calories = CaloriesCaculator.CAL_DUMBELL_DEADLIFT;
        }

        if (name.equals(CaloriesCaculator.BARBELL_BENCH_PRESS))
        {
            calories = CaloriesCaculator.CAL_BARBELL_BENCH_PRESS;
        }


    }

    @Override
    public void onBackPressed() {
        if (mediaPlayer!=null)
        {
            mediaPlayer.stop();
        }
        if (countDownTimer != null)
        {
            countDownTimer.cancel();
        }
        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        if (mediaPlayer!=null)
        {
            mediaPlayer.stop();
        }
        if (countDownTimer != null)
        {
            countDownTimer.cancel();
        }
        super.onStop();
    }
}
