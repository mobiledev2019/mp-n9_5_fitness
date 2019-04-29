package com.example.datnguyen.fitness.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.datnguyen.fitness.Model.WeatherResult;
import com.example.datnguyen.fitness.Others.Common;
import com.example.datnguyen.fitness.R;
import com.example.datnguyen.fitness.Retrofit.IOpenWeatherMap;
import com.example.datnguyen.fitness.Retrofit.RetrofitClient;
import com.squareup.picasso.Picasso;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodayWeatherFragment extends Fragment {
    View view;
    ImageView img_weather,img_Outdoor,img_Indoor;
    TextView  txt_city_name, txt_humidity, txt_sunrise, txt_sunset,txt_pressure, txt_temperature, txt_description,txt_date_time, txt_wind, txt_geo_coord;
    LinearLayout weather_panel;
    ProgressBar loading;
    CompositeDisposable compositeDisposable;
    IOpenWeatherMap mService;
    static TodayWeatherFragment instance;

    public TodayWeatherFragment(){
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetrofitClient.getInstance();
        mService = retrofit.create(IOpenWeatherMap.class);
    }
    public static TodayWeatherFragment getInstance() {
        if (instance == null)
            instance = new TodayWeatherFragment();
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_today_weather,null,false);

        img_weather = view.findViewById(R.id.img_weather);
        img_Outdoor = view.findViewById(R.id.imgOutdoor);
        img_Indoor = view.findViewById(R.id.imgIndoor);

        txt_city_name = view.findViewById(R.id.txt_city_name);
        txt_humidity = view.findViewById(R.id.txt_humidity);
        txt_sunrise = view.findViewById(R.id.txt_sunrise);
        txt_sunset = view.findViewById(R.id.txt_sunset);
        txt_pressure = view.findViewById(R.id.txt_pressure);
        txt_temperature = view.findViewById(R.id.txt_temperature);
        txt_description = view.findViewById(R.id.txt_description);
        txt_date_time = view.findViewById(R.id.txt_date_time);
        txt_wind = view.findViewById(R.id.txt_wind);
        txt_geo_coord = view.findViewById(R.id.txt_geo_coord);

        weather_panel = view.findViewById(R.id.weather_panel);
        loading = view.findViewById(R.id.loading);


        getWeatherInformation();





        return view;
    }

    private void getWeatherInformation() {
        compositeDisposable.add(mService.getWeatherByLatLng(String.valueOf(Common.current_location.getLatitude()),
                String.valueOf(Common.current_location.getLongitude()),
                 Common.API_ID,
                "metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherResult>() {
                    @Override
                    public void accept(WeatherResult weatherResult) throws Exception {
                        //Load Image
                        Picasso.with(getContext()).load(new StringBuilder("https://openweathermap.org/img/w/")
                                .append(weatherResult.getWeather().get(0).getIcon())
                                .append(".png").toString()).into(img_weather);

                        setSuggestion(weatherResult);
                        // Get Weather Info
                        txt_city_name.setText(weatherResult.getName());
                        txt_description.setText(new StringBuilder("Weather in ")
                        .append(weatherResult.getName()).toString());

                        txt_temperature.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getTemp())).append("°C").toString());

                        txt_date_time.setText(Common.convertUnixToDate(weatherResult.getDt()));

                        txt_pressure.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getPressure())).append(" hpa").toString());

                        txt_humidity.setText(new StringBuilder(String.valueOf(weatherResult.getMain().getHumidity())).append(" %").toString());

                        txt_sunrise.setText(Common.convertUnixToHour(weatherResult.getSys().getSunrise()));
                        txt_sunset.setText(Common.convertUnixToHour(weatherResult.getSys().getSunset()));

                        txt_geo_coord.setText(new StringBuilder().append(weatherResult.getCoord().toString()).toString());



                        //Display panel
                        weather_panel.setVisibility(View.VISIBLE);
                        loading.setVisibility(View.GONE);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getActivity(), ""+throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })

        );
    }

    private void setSuggestion(WeatherResult weatherResult) {
        // it's raining
        if (weatherResult.getWeather().get(0).getIcon().equals(Common.RAIN_V1))
        {
            img_Indoor.setImageResource(R.drawable.happy_icon);
            img_Outdoor.setImageResource(R.drawable.sad_icon);
        }
        else if (weatherResult.getWeather().get(0).getIcon().equals(Common.RAIN_V2))
        {
            img_Indoor.setImageResource(R.drawable.happy_icon);
            img_Outdoor.setImageResource(R.drawable.sad_icon);
        }
        // it's hot
        else if (weatherResult.getMain().getTemp() > 33)
        {
            img_Indoor.setImageResource(R.drawable.happy_icon);
            img_Outdoor.setImageResource(R.drawable.sad_icon);
        }
        else {
            img_Indoor.setImageResource(R.drawable.normal_emotion);
            img_Outdoor.setImageResource(R.drawable.normal_emotion);
        }

    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}
