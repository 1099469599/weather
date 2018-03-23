package com.lpx.weather.service;

import com.lpx.weather.model.City;
import com.lpx.weather.model.Weather;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Author: lipingxin
 * @Description:
 * @Date: 2018/3/23 16:29
 */

@FeignClient("weather-eureka-client-zuul")
public interface IDataClient {

    @GetMapping("/city/city")
    List<City> getCityList();

    @GetMapping("/data/weather/future/{city}")
    Weather getFutureWeather(@PathVariable("city") String city);

    @GetMapping("/data/weather/current/{city}")
    Weather getCurrentWeather(@PathVariable("city") String city);


}
