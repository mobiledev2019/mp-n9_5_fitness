package com.example.datnguyen.fitness.Activity.Exercises;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.datnguyen.fitness.Database.WorkoutDB;
import com.example.datnguyen.fitness.Model.Exercise;
import com.example.datnguyen.fitness.Others.Common;
import com.example.datnguyen.fitness.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CardioTraining extends AppCompatActivity {
    Button btnStart;
    ImageView ex_image, img_sound;
    TextView txtGetReady, txtCountdown,txtTimer,ex_name;
    ProgressBar progressBar;
    LinearLayout layoutGetReady;
    MediaPlayer mediaPlayer;
    CountDownTimer getReady;


    int ex_id = 0;
    WorkoutDB workoutDB;

    List<Exercise> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardio_training);
        //Animation
        overridePendingTransition(R.anim.fade_in,R.anim.zoom_out);


        workoutDB = new WorkoutDB(this);
        initData();
        // mapping
        btnStart = findViewById(R.id.btnStart);

        ex_image = findViewById(R.id.detail_image);
        img_sound = findViewById(R.id.imgSoud);

        txtCountdown= findViewById(R.id.txtCountDown);
        txtGetReady = findViewById(R.id.txtGetReady);
        txtTimer = findViewById(R.id.timer);
        ex_name =findViewById(R.id.titleEx);

        progressBar = findViewById(R.id.progressBar);

        layoutGetReady = findViewById(R.id.layout_get_ready);

        //Set data
        progressBar.setMax(list.size());

        setExerciseInformation(ex_id);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (btnStart.getText().toString().toLowerCase().equals("start"))
                {
                    showGetReady();
                    btnStart.setText("done");
                }
                else if (btnStart.getText().toString().toLowerCase().equals("done"))
                {
                    mediaPlayer.stop();
                    // if click done while exercise is running
                    if (workoutDB.getSettingMode() == 0)
                    {
                        exerciseEasyCountdown.cancel();
                    }
                    else   if (workoutDB.getSettingMode() == 1)
                    {
                        exerciseMediumCountdown.cancel();
                    }
                    else   if (workoutDB.getSettingMode() == 2)
                    {
                        exerciseHardCountdown.cancel();
                    }
                    restTimeCountdown.cancel();

                    if (ex_id < list.size())
                    {
                        showRestTime();
                        ex_id++;
                        progressBar.setProgress(ex_id);
                        txtTimer.setText("");
                    }
                    else showFinished();
                }
                else
                {
                    if (workoutDB.getSettingMode() == 0)
                    {
                        exerciseEasyCountdown.cancel();
                    }
                    else   if (workoutDB.getSettingMode() == 1)
                    {
                        exerciseMediumCountdown.cancel();
                    }
                    else   if (workoutDB.getSettingMode() == 2)
                    {
                        exerciseHardCountdown.cancel();
                    }

                    restTimeCountdown.cancel();

                    if (ex_id < list.size())
                    {
                        setExerciseInformation(ex_id);
                    }
                    else showFinished();

                }

            }
        });
    }
    private void showRestTime() {

        ex_image.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.VISIBLE);
        btnStart.setText("Skip");

        layoutGetReady.setVisibility(View.VISIBLE);

        restTimeCountdown.start();

        txtGetReady.setText("REST TIME");
    }

    private void showFinished() {
        mediaPlayer.stop();
        ex_image.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.INVISIBLE);

        layoutGetReady.setVisibility(View.VISIBLE);

        txtGetReady.setText("FINISHED!!!");
        txtCountdown.setText("Congratulations ! \nYou have just finished today cardio ");
        txtCountdown.setTextSize(20);

        //save done workout
        workoutDB.saveDay(""+Calendar.getInstance().getTimeInMillis());
    }

    private void showGetReady() {
        ex_image.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.INVISIBLE);
        txtTimer.setVisibility(View.VISIBLE);

        layoutGetReady.setVisibility(View.VISIBLE);

        txtGetReady.setText("GET READY");
        getReady = new CountDownTimer(6000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                txtCountdown.setText(""+(millisUntilFinished-1000)/1000);
            }

            @Override
            public void onFinish() {
                showExercise();
                mediaPlayer = MediaPlayer.create(CardioTraining.this,R.raw.portals_alansilvestri);
                mediaPlayer.start();
                //image_sound
                img_sound.setImageResource(R.drawable.soud_on);
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
            }
        }.start();
    }

    private void showExercise() {
        if (ex_id < list.size()) // list size contains all excersices
        {
            ex_image.setVisibility(View.VISIBLE);
            btnStart.setVisibility(View.VISIBLE);
            layoutGetReady.setVisibility(View.INVISIBLE);

            if (workoutDB.getSettingMode() == 0)
            {
                exerciseEasyCountdown.start();
            }
            else   if (workoutDB.getSettingMode() == 1)
            {
                exerciseMediumCountdown.start();
            }
            else   if (workoutDB.getSettingMode() == 2)
            {
                exerciseHardCountdown.start();
            }


            //set data
          //  ex_image.setImageResource(list.get(ex_id).getImage_id());
            Glide.with(this).load(list.get(ex_id).getImage_id()).into(ex_image);
            ex_name.setText(list.get(ex_id).getName());
        }
        else {
            showFinished();
        }
    }
    //Countdown
    CountDownTimer exerciseEasyCountdown = new CountDownTimer(Common.TIME_LIMIT_EASY,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            txtTimer.setText(""+(millisUntilFinished/1000));
        }

        @Override
        public void onFinish() {
            mediaPlayer.stop();
            if (ex_id < list.size()-1){
                ex_id++ ;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");

                setExerciseInformation(ex_id);
                btnStart.setText("Start");
            }
            else {
                showFinished();
            }
        }
    };
    CountDownTimer exerciseMediumCountdown = new CountDownTimer(Common.TIME_LIMIT_MEDIUM,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            txtTimer.setText(""+(millisUntilFinished/1000));
        }

        @Override
        public void onFinish() {
            mediaPlayer.stop();
            if (ex_id < list.size()-1){
                ex_id++ ;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");

                setExerciseInformation(ex_id);
                btnStart.setText("Start");
            }
            else {
                showFinished();
            }
        }
    };
    CountDownTimer exerciseHardCountdown = new CountDownTimer(Common.TIME_LIMIT_HARD,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            txtTimer.setText(""+(millisUntilFinished/1000));
        }

        @Override
        public void onFinish() {
            mediaPlayer.stop();
            if (ex_id < list.size()-1){
                ex_id++ ;
                progressBar.setProgress(ex_id);
                txtTimer.setText("");

                setExerciseInformation(ex_id);
                btnStart.setText("Start");
            }
            else {
                showFinished();
            }
        }
    };

    CountDownTimer restTimeCountdown = new CountDownTimer(10000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            txtCountdown.setText(""+(millisUntilFinished/1000));
        }

        @Override
        public void onFinish() {
            mediaPlayer = MediaPlayer.create(CardioTraining.this,R.raw.portals_alansilvestri);
            mediaPlayer.start();
            setExerciseInformation(ex_id);
            showExercise();
        }
    };

    private void setExerciseInformation(int id) {
  //      ex_image.setImageResource(list.get(id).getImage_id());
        Glide.with(this).load(list.get(id).getImage_id()).into(ex_image);
        ex_name.setText(list.get(id).getName());
        btnStart.setText("Start");

        ex_image.setVisibility(View.VISIBLE);
        btnStart.setVisibility(View.VISIBLE);
        txtTimer.setVisibility(View.VISIBLE);

        layoutGetReady.setVisibility(View.INVISIBLE);
    }

    private void initData() {
        list.add(new Exercise(R.drawable.cardio_running,"Running"));
        list.add(new Exercise(R.drawable.cardio_jumping,"Jumping"));
        list.add(new Exercise(R.drawable.cardio_running,"Running"));
        list.add(new Exercise(R.drawable.cardio1,"Jumping"));
        list.add(new Exercise(R.drawable.cardio_rope,"Rope jumping"));

        }

    @Override
    public void onBackPressed() {
        if (mediaPlayer!= null)
        {
            mediaPlayer.stop();
        }
        restTimeCountdown.cancel();
        exerciseEasyCountdown.cancel();
        exerciseMediumCountdown.cancel();
        exerciseHardCountdown.cancel();
        if (getReady != null)
        {
            getReady.cancel();
        }

        super.onBackPressed();
    }

    @Override
    protected void onStop() {
        if (mediaPlayer!= null)
        {
            mediaPlayer.stop();
        }
        restTimeCountdown.cancel();
        exerciseEasyCountdown.cancel();
        exerciseMediumCountdown.cancel();
        exerciseHardCountdown.cancel();
        if (getReady != null)
        {
            getReady.cancel();
        }
        super.onStop();
    }
}
