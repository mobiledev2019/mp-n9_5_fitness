package com.example.datnguyen.fitness.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.datnguyen.fitness.Model.WeatherForecastResult;
import com.example.datnguyen.fitness.Others.Common;
import com.example.datnguyen.fitness.R;
import com.squareup.picasso.Picasso;

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.MyViewHolder> {
    Context context;
    WeatherForecastResult weatherForecastResult;

    public WeatherForecastAdapter(Context context, WeatherForecastResult weatherForecastResult) {
        this.context = context;
        this.weatherForecastResult = weatherForecastResult;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_weather_forecast,viewGroup,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        // Load Icon

        Picasso.with(context).load(new StringBuilder("https://openweathermap.org/img/w/")
                .append(weatherForecastResult.list.get(i).weather.get(0).getIcon())
                .append(".png").toString()).into(myViewHolder.img_weather);

        myViewHolder.txt_date_time.setText(new StringBuilder(Common.convertUnixToDate(weatherForecastResult
        .list.get(i).dt)));

        myViewHolder.txt_description.setText(new StringBuilder(weatherForecastResult.list.get(i)
        .weather.get(0).getDescription()));

        myViewHolder.txt_temperature.setText(new StringBuilder(String.valueOf(weatherForecastResult.list.get(i)
        .main.getTemp())).append("°C"));


    }

    @Override
    public int getItemCount() {
        return weatherForecastResult.list.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder {
        TextView txt_date_time, txt_description, txt_temperature;
        ImageView img_weather;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            img_weather = itemView.findViewById(R.id.img_weather);
            txt_date_time = itemView.findViewById(R.id.txt_date);
            txt_description = itemView.findViewById(R.id.txt_description);
            txt_temperature = itemView.findViewById(R.id.txt_temperature);
        }
    }
}
