package com.example.datnguyen.fitness.Activity.Home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.datnguyen.fitness.R;

public class SplashScreen extends AppCompatActivity {
    TextView txtSplash;
    ImageView imgSplash;
    ImageView imgWeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        txtSplash = findViewById(R.id.txtSplash);
        imgSplash = findViewById(R.id.imgSplash);
        imgWeight = findViewById(R.id.imgWeight);

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.bounce);
        txtSplash.startAnimation(animation);
        imgSplash.startAnimation(animation);

        Animation animation2 = AnimationUtils.loadAnimation(this,R.anim.rotate);
        imgWeight.startAnimation(animation2);
        final Intent intent = new Intent(SplashScreen.this,MainActivity.class);
        Thread timer = new Thread(){
            public void run(){
                try{
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    startActivity(intent);
                    finish();
                }
            }
        };
            timer.start();
    }
}
