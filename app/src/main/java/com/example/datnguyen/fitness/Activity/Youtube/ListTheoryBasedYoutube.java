package com.example.datnguyen.fitness.Activity.Youtube;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.datnguyen.fitness.Adapter.VideoAdapter;
import com.example.datnguyen.fitness.Model.Video;
import com.example.datnguyen.fitness.Others.Common;
import com.example.datnguyen.fitness.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListTheoryBasedYoutube extends AppCompatActivity {
    // ListView
    ListView lvVideo;
    ArrayList<Video> videos;
    VideoAdapter adapter;

    // Youtube API
    public static String API_KEY = Common.API_YOUTUBE;
    String ID_PLAYLIST = "PL1Aqc075hBOcKmB6mdwPqlBopNJZpp61G";
    int REQUEST_VIDEO = 1;
    String url = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="+ID_PLAYLIST+"&key="+API_KEY+"&maxResults=50";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_youtube_exercise);
        //Animation
        overridePendingTransition(R.anim.fade_in,R.anim.zoom_out);

        // set Adapter
        lvVideo = findViewById(R.id.lvVideo);
        videos = new ArrayList<>();
        adapter = new VideoAdapter(ListTheoryBasedYoutube.this,R.layout.row_video,videos);
        lvVideo.setAdapter(adapter);
        //Read JSON
        getJSON(url);
        //event handling
        lvVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListTheoryBasedYoutube.this,PlayYoutubeVideo.class);
                intent.putExtra("idVideo",videos.get(position).getId());
                startActivity(intent);
            }
        });
    }

    private void getJSON(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonItems = response.getJSONArray("items");
                    String title = "";
                    String urlJSON = "";
                    String videoId ="";
                    for (int i= 0 ; i< jsonItems.length(); i++)
                    {
                        JSONObject jsonItem = jsonItems.getJSONObject(i);
                        JSONObject jsonSnippet = jsonItem.getJSONObject("snippet");
                        title = jsonSnippet.getString("title");
                        JSONObject jsonThump = jsonSnippet.getJSONObject("thumbnails");
                        JSONObject jsonMedium = jsonThump.getJSONObject("medium");
                        urlJSON = jsonMedium.getString("url");
                        JSONObject jsonResourceId = jsonSnippet.getJSONObject("resourceId");
                        videoId = jsonResourceId.getString("videoId");
                        videos.add(new Video(title,urlJSON,videoId));


                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListTheoryBasedYoutube.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
}
