package com.example.datnguyen.fitness.Adapter;

import android.content.Context;
import android.graphics.Color;
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

        // set suggestion
        // it's raining
        if (weatherForecastResult.list.get(i).weather.get(0).getIcon().equals(Common.RAIN_V1))
        {
            myViewHolder.img_Indoor.setImageResource(R.drawable.happy_icon);
            myViewHolder.img_Outdoor.setImageResource(R.drawable.sad_icon);
        }
        else if (weatherForecastResult.list.get(i).weather.get(0).getIcon().equals(Common.RAIN_V2))
        {
            myViewHolder.img_Indoor.setImageResource(R.drawable.happy_icon);
            myViewHolder.img_Outdoor.setImageResource(R.drawable.sad_icon);
        }
        // it's hot
        else if (weatherForecastResult.list.get(i).main.getTemp() > 33)
        {
            myViewHolder.img_Indoor.setImageResource(R.drawable.happy_icon);
            myViewHolder.img_Outdoor.setImageResource(R.drawable.sad_icon);
        }
        else {
            myViewHolder.img_Indoor.setImageResource(R.drawable.normal_emotion);
            myViewHolder.img_Outdoor.setImageResource(R.drawable.normal_emotion);
        }


    }

    @Override
    public int getItemCount() {
        return weatherForecastResult.list.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder {
        TextView txt_date_time, txt_description, txt_temperature;
        ImageView img_weather, img_Indoor, img_Outdoor;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_weather = itemView.findViewById(R.id.img_weather);
            img_Indoor = itemView.findViewById(R.id.imgForecastIndoor);
            img_Outdoor = itemView.findViewById(R.id.imgForecastOutdoor);

            txt_date_time = itemView.findViewById(R.id.txt_date);
            txt_description = itemView.findViewById(R.id.txt_description);
            txt_temperature = itemView.findViewById(R.id.txt_temperature);
        }
    }
}
