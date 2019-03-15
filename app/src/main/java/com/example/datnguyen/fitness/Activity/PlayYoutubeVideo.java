package com.example.datnguyen.fitness.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.datnguyen.fitness.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayYoutubeVideo extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{
    YouTubePlayerView youTubePlayerView;
    String id = "";
    int REQUEST_VIDEO = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_youtube_video);
        youTubePlayerView = findViewById(R.id.myYoutube);
        Intent intent = getIntent();
        id = intent.getStringExtra("idVideo");
        youTubePlayerView.initialize(ListYoutubeExercise.API_KEY,this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(id);
        youTubePlayer.setFullscreen(true);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if (youTubeInitializationResult.isUserRecoverableError())
        {
            youTubeInitializationResult.getErrorDialog(PlayYoutubeVideo.this,REQUEST_VIDEO);

        }
        else {
            Toast.makeText(this, "Error!!", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_VIDEO){
            youTubePlayerView.initialize(ListYoutubeExercise.API_KEY,PlayYoutubeVideo.this);

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
