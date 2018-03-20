package com.lpx.weather.service;

import com.lpx.weather.model.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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

    private static final String FUTURE_WEATHER_REDIS_KEY = "future-weather-";
    private static final String CURRENT_WEATHER_REDIS_KEY = "current-weather-";

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public Weather getFutureWeather(String city) {

        // 从缓存中查询天气数据
        String key = FUTURE_WEATHER_REDIS_KEY + city;
        Weather weather = queryFromRedis(key);
        if (weather == null) {
            LOGGER.error("缓存中没有未来天气数据");
            throw new RuntimeException("There's no data in cache");
        }

        return null;
    }

    @Override
    public Weather getCurrentWeather(String city) {

        // 从缓存中查询天气数据
        String key = CURRENT_WEATHER_REDIS_KEY + city;
        Weather weather = queryFromRedis(key);
        if (weather == null) {
            LOGGER.error("缓存中没有实况天气数据");
            throw new RuntimeException("There's no data in cache");
        }

        return weather;
    }


    // 从缓存中查询天气数据
    private Weather queryFromRedis(String key) {
        if (redisTemplate.hasKey(key)) {
            ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
            Weather weather = (Weather) valueOperations.get(key);
//            Weather weather = JSONObject.parseObject(weatherData, Weather.class);
            LOGGER.info("************************ 从Redis中取到数据 ************************");
            return weather;
        }
        return null;
    }

}
