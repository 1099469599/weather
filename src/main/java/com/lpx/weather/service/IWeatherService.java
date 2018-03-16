package com.lpx.weather.service;

import com.lpx.weather.model.Weather;

/**
 * description:
 * author: lpx
 * mail: lipingxin@outlook.com
 * time: 2018-03-14 22:37.
 */
public interface IWeatherService {

    Weather getFutureWeather(String city);

    Weather getCurrentWeather(String city);

    void syncWeatherData(String cityId);
}
