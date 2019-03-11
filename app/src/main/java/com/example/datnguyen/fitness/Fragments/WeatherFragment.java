package com.example.datnguyen.fitness.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.datnguyen.fitness.Activity.ListWeatherActivity;
import com.example.datnguyen.fitness.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherFragment extends Fragment {
    private EditText edtSearch;
    private Button btnSearch, btnNext;
    private TextView txtName, txtNation, txtTemp, txtState, txtHumidity, txtCloud, txtWind, txtDay;
    private ImageView imgIcon;
    public View view;
    private String city;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_weather,container,false);
        init();
        getCurrentWeatherData("Hanoi");
        // btnSearch
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   String current_city = edtSearch.getText().toString().trim();
                if (current_city.isEmpty())
                {
                    city = "Hanoi";
                    Toast.makeText(getContext(), "Please, enter a city!!!", Toast.LENGTH_SHORT).show();;
                }
                else {
                    city = current_city;
                    getCurrentWeatherData(city);
                }
            }
        });
        //btnNext
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),ListWeatherActivity.class);
                intent.putExtra("city",city);
                startActivity(intent);
            }
        });

        return view;
    }
    // initialize
    private void init() {
        edtSearch = view.findViewById(R.id.edtSearch);
        btnSearch = view.findViewById(R.id.btnSearch);
        btnNext = view.findViewById(R.id.btnNext);
        txtName = view.findViewById(R.id.txtName);
        txtNation = view.findViewById(R.id.txtNation);
        txtTemp = view.findViewById(R.id.txtTemp);
        txtState = view.findViewById(R.id.txtState);
        txtHumidity = view.findViewById(R.id.txtHumidity);
        txtCloud = view.findViewById(R.id.txtCloud);
        txtWind = view.findViewById(R.id.txtWind);
        txtDay = view.findViewById(R.id.txtDay);
        imgIcon = view.findViewById(R.id.imgIcon);
    }
    public void getCurrentWeatherData(final String data){
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+data+"&units=metric&appid=c0e28b1d2b515d47102ee637e8991c16";
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String date = jsonObject.getString("dt");
                    String name = jsonObject.getString("name");
                    //set City
                    txtName.setText(name);
                    //set Date
                    long temp = Long.valueOf(date);
                    Date date_temp = new Date(temp*1000);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE yyyy-MM-dd");
                    String string_Day = simpleDateFormat.format(date_temp);
                    txtDay.setText(string_Day);

                    JSONArray jsonArrayWeather = jsonObject.getJSONArray("weather");
                    JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
                    String state = jsonObjectWeather.getString("main");
                    String icon =jsonObjectWeather.getString("icon");
                    //set icon
                    Picasso.with(getContext()).load("http://openweathermap.org/img/w/"+icon+".png").into(imgIcon);
                    //set state
                    txtState.setText(state);

                    JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
                    String temperature = jsonObjectMain.getString("temp");
                    String humidity = jsonObjectMain.getString("humidity");
                    // set temp && humidity
                    Double temp_double = Double.valueOf(temperature);
                    String intTemperature = String.valueOf(temp_double.intValue());
                    txtTemp.setText(intTemperature+" C");
                    txtHumidity.setText(humidity+"%");

                    JSONObject jsonObjectWind = jsonObject.getJSONObject("wind");
                    //set speed of wind
                    String wind = jsonObjectWind.getString("speed");
                    txtWind.setText(wind+" m/s");

                    //set clouds
                    JSONObject jsonObjectClouds = jsonObject.getJSONObject("clouds");
                    String clouds = jsonObjectClouds.getString("all");
                    txtCloud.setText(clouds+" %");

                    //set country
                    JSONObject jsonObjectSys = jsonObject.getJSONObject("sys");
                    String country = jsonObjectSys.getString("country");
                    txtNation.setText(country);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);
    }
}
