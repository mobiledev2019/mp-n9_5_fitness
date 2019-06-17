package com.example.datnguyen.fitness.Activity.Youtube;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.datnguyen.fitness.R;

public class YoutubePlaylist extends AppCompatActivity {
    private ImageView imgTheory, imgChest, imgArms, imgLegs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_playlist);
        imgTheory = findViewById(R.id.imgTheory);
        imgChest = findViewById(R.id.imgChest);
        imgArms = findViewById(R.id.imgArms);
        imgLegs = findViewById(R.id.imgLegs);

        //Animation
        overridePendingTransition(R.anim.slide_left,R.anim.zoom_out);



        //handle events
        imgTheory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkConnected())
                startActivity(new Intent(YoutubePlaylist.this,ListTheoryBasedYoutube.class));
                else Toast.makeText(YoutubePlaylist.this, "No Internet Connection !!!", Toast.LENGTH_SHORT).show();
            }
        });

        imgChest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkConnected())
                startActivity(new Intent(YoutubePlaylist.this,ListChestYoutube.class));
                else Toast.makeText(YoutubePlaylist.this, "No Internet Connection !!!", Toast.LENGTH_SHORT).show();
            }
        });

        imgLegs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkConnected())
                    startActivity(new Intent(YoutubePlaylist.this,ListLegsYoutube.class));
                else Toast.makeText(YoutubePlaylist.this, "No Internet Connection !!!", Toast.LENGTH_SHORT).show();
            }
        });

        imgArms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNetworkConnected())
                    startActivity(new Intent(YoutubePlaylist.this,ListArmsYoutube.class));
                else Toast.makeText(YoutubePlaylist.this, "No Internet Connection !!!", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }
}
