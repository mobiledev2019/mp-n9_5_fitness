package com.example.datnguyen.fitness.Retrofit;

import com.example.datnguyen.fitness.Model.WeatherForecastResult;
import com.example.datnguyen.fitness.Model.WeatherResult;

import java.util.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IOpenWeatherMap {
    @GET ("weather")
    io.reactivex.Observable<WeatherResult> getWeatherByLatLng(@Query("lat") String lat,
                                                              @Query("lon") String lng,
                                                              @Query("appid") String appid,
                                                              @Query("units") String unit);


    @GET ("forecast")
    io.reactivex.Observable<WeatherForecastResult> getForecastWeatherByLatLng(@Query("lat") String lat,
                                                                              @Query("lon") String lng,
                                                                              @Query("appid") String appid,
                                                                              @Query("units") String unit);


}
