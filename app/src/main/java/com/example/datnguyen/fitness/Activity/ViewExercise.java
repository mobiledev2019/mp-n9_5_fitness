package com.example.datnguyen.fitness.Activity;

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
import com.example.datnguyen.fitness.Database.WorkoutDB;
import com.example.datnguyen.fitness.Others.Common;
import com.example.datnguyen.fitness.R;

public class ViewExercise extends AppCompatActivity {

    int image_id;
    String name;
    TextView timer, title;
    ImageView detail_image, img_sound;
    Button btnStart;
    // check btnStart's state
    boolean isRunning = false;
    MediaPlayer mediaPlayer;
    WorkoutDB workoutDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercise);
        workoutDB = new WorkoutDB(this);

        timer = findViewById(R.id.timer);
        title = findViewById(R.id.titleEx);
        detail_image = findViewById(R.id.detail_image);
        img_sound = findViewById(R.id.img_sound);

        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                // Timer
                if (!isRunning)
                {
                    mediaPlayer = MediaPlayer.create(ViewExercise.this,R.raw.portals_alansilvestri);
                    mediaPlayer.start();


                    //
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
                    }
                    else if (workoutDB.getSettingMode() == 1)
                    {
                        timeLimit = Common.TIME_LIMIT_MEDIUM;
                    }
                    else if (workoutDB.getSettingMode() == 2)
                    {
                        timeLimit = Common.TIME_LIMIT_HARD;
                    }

                    new CountDownTimer(timeLimit,1000){
                        @Override
                        public void onTick(long millisUntilFinished) {
                            timer.setText(""+millisUntilFinished/1000);
                        }

                        @Override
                        public void onFinish() {
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
        // get intent from ListExercise
        if (getIntent() != null)
        {
            image_id = getIntent().getIntExtra("image_id",-1);
            name = getIntent().getStringExtra("name");

          //  detail_image.setImageResource(image_id);
            Glide.with(this).load(image_id).into(detail_image);
            title.setText(name);

        }
    }
}
