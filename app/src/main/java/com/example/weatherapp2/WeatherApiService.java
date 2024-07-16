package com.example.weatherapp2;

import com.example.weatherapp2.model.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApiService {
    @GET("weather")
    Call<WeatherData> getWeather(
            @Query("q") String cityName,
            @Query("appid") String apiKey,
            @Query("units") String unit
    );
}

