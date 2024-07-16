package com.example.weatherapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.bumptech.glide.Glide;
import com.example.weatherapp2.databinding.ActivityMainBinding;
import com.example.weatherapp2.model.WeatherData;
import com.example.weatherapp2.model.clouds;
import com.example.weatherapp2.model.main;
import com.example.weatherapp2.model.weather;
import com.example.weatherapp2.model.wind;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private WeatherApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
         apiService = retrofit.create(WeatherApiService.class);

        LottieAnimationView animationView = binding.animationView;
        animationView.setAnimation(R.raw.sunrise);
        animationView.setRepeatCount(LottieDrawable.INFINITE);
        animationView.playAnimation();

        SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
        String currentDate = format.format(new Date());

        binding.date.setText(currentDate);

        binding.searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(binding.cityName.getText().toString())){
                    String cityName = binding.cityName.getText().toString();
                    fetchWeatherData(cityName);
                }
                else{
                    Toast.makeText(MainActivity.this, "Please Enter City Name", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }

    private void fetchWeatherData(String cityName) {
        Call<WeatherData> call = apiService.getWeather(cityName, "7b0d90ca01411bff82bfe61ea542b533", "metric");
        call.enqueue(new Callback<WeatherData>() {
            @Override
            public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                if (response.isSuccessful()) {
                    WeatherData weatherData = response.body();
                    clouds clouds = weatherData.getClouds();
                    wind wind = weatherData.getWind();
                    main main = weatherData.getMain();
                    binding.range.setText(String.valueOf(main.getTemp_min())+"-"+String.valueOf(main.getTemp_max()));
                    binding.humidity.setText(String.valueOf(main.getHumidity()));
                    binding.pressure.setText(String.valueOf(main.getPressure()));
                    binding.mainTemp.setText(String.valueOf(main.getTemp()) + " celcius");
                    binding.name.setText(weatherData.getName());
                    binding.windSpeed.setText(String.valueOf(wind.getSpeed()));
                    binding.clouds.setText(String.valueOf(clouds.getAll()));


                    List<weather> details = weatherData.getWeather();
                    for (weather data : details){
                        binding.details.setText(data.getDescription());
                        String iconUrl = "https://openweathermap.org/img/w/" + weatherData.getWeather().get(0).getIcon() + ".png";

                        Glide.with(getApplicationContext())
                                .load(iconUrl)
                                .into(binding.icon);
                    }
                }


            }

            @Override
            public void onFailure(Call<WeatherData> call, Throwable t) {

            }
        });
    }
}