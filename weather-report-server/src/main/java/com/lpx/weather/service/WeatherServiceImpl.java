package com.lpx.weather.service;

import com.lpx.weather.model.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * description:
 * author: lpx
 * mail: lipingxin@outlook.com
 * time: 2018-03-14 22:39.
 */
@Service
public class WeatherServiceImpl implements IWeatherService {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherServiceImpl.class);

    @Override
    public Weather getFutureWeather(String city) {
        // TODO 从其他微服务获取数据
        return null;
    }

    @Override
    public Weather getCurrentWeather(String city) {
        // TODO 从其他微服务获取数据
        return null;
    }


}
