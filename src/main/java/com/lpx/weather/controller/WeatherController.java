package com.lpx.weather.controller;

import com.lpx.weather.model.Weather;
import com.lpx.weather.service.IWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 * author: lpx
 * mail: lipingxin@outlook.com
 * time: 2018-03-14 23:19.
 */

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private IWeatherService weatherService;

    @GetMapping("future/{city}")
    public Weather getFutureWeather(@PathVariable String city) {
        return weatherService.getFutureWeather(city);
    }

    @GetMapping("current/{city}")
    public Weather getCurrentWeather(@PathVariable String city) {
        return weatherService.getCurrentWeather(city);
    }

}
