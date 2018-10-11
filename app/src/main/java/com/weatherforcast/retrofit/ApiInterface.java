package com.weatherforcast.retrofit;


import com.weatherforcast.weatherData.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * ApiInterface
 */
@SuppressWarnings("ALL")
public interface ApiInterface {

    @GET("/data/2.5/forecast?id=1274746&appid=b0389e094004fdc5e8a0c8aa8ba5bbb9&units=metric")
    Call<WeatherData> getWeatherData();


}
