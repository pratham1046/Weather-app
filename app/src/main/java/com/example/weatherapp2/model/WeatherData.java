package com.example.weatherapp2.model;

import java.util.List;

public class WeatherData {
    private List<weather> weather;
    private main main;
    private String name;
    private wind wind;
    private clouds clouds;

    public WeatherData(List<com.example.weatherapp2.model.weather> weather, com.example.weatherapp2.model.main main, String name, com.example.weatherapp2.model.wind wind, com.example.weatherapp2.model.clouds clouds) {
        this.weather = weather;
        this.main = main;
        this.name = name;
        this.wind = wind;
        this.clouds = clouds;
    }

    public List<com.example.weatherapp2.model.weather> getWeather() {
        return weather;
    }

    public void setWeather(List<com.example.weatherapp2.model.weather> weather) {
        this.weather = weather;
    }

    public com.example.weatherapp2.model.main getMain() {
        return main;
    }

    public void setMain(com.example.weatherapp2.model.main main) {
        this.main = main;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public com.example.weatherapp2.model.wind getWind() {
        return wind;
    }

    public void setWind(com.example.weatherapp2.model.wind wind) {
        this.wind = wind;
    }

    public com.example.weatherapp2.model.clouds getClouds() {
        return clouds;
    }

    public void setClouds(com.example.weatherapp2.model.clouds clouds) {
        this.clouds = clouds;
    }
}
