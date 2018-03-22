package com.lpx.weather.service;

import com.lpx.weather.model.Weather;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * description:
 * author: lpx
 * mail: lipingxin@outlook.com
 * time: 2018-03-14 22:37.
 */
@FeignClient("weather-data-api")
public interface IWeatherService {

    @GetMapping("/weather/future/{city}")
    Weather getFutureWeather(@PathVariable("city") String city);

    @GetMapping("/weather/current/{city}")
    Weather getCurrentWeather(@PathVariable("city") String city);

}
