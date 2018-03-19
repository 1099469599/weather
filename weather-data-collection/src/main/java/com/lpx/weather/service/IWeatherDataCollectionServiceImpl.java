package com.lpx.weather.service;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.lpx.weather.model.City;
import com.lpx.weather.model.Forecast;
import com.lpx.weather.model.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @Author: lipingxin
 * @Description:
 * @Date: 2018/3/19 18:36
 */
@Service
public class IWeatherDataCollectionServiceImpl implements IWeatherDataCollectionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IWeatherDataCollectionServiceImpl.class);

    private static final String BASE_URL = "https://free-api.heweather.com/s6/";
    private static final String FUTURE_WEATHER_REDIS_KEY = "future-weather-";
    private static final String CURRENT_WEATHER_REDIS_KEY = "current-weather-";
    private static final int REDIS_TIME_OUT = 30 * 60;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void syncDataByCity(String city) {
        String key = CURRENT_WEATHER_REDIS_KEY + city;
        Weather weather = requestCurrentWeather(city, key);
        if (weather != null) {
            // 将天气数据放入缓存
            setDataToRedis(key, weather);
        }
    }

    // 发起请求，查询即时天气
    private Weather requestCurrentWeather(String city, String key) {
        Weather weather;
        String url = BASE_URL + "weather/now?key=ee0925789ffd4401aa8407d5fecee272&location=" + city;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        if (responseEntity != null && Objects.equals(HttpStatus.OK, responseEntity.getStatusCode())) {
            String responseBody = responseEntity.getBody();
            if (!StringUtils.isEmpty(responseBody)) {
                try {
                    weather = new Weather();
                    JSONObject json = JSONObject.parseObject(responseBody);
                    JSONObject heWeather = json.getJSONArray("HeWeather6").getJSONObject(0);

                    // 格式化 城市信息
                    JSONObject cityInfo = heWeather.getJSONObject("basic");
                    City cityObject = JSONObject.parseObject(cityInfo.toString(), City.class);
                    weather.setCity(cityObject);

                    // 处理天气数据
                    JSONObject forecastInfo = heWeather.getJSONObject("now");
                    List<Forecast> forecastList = new ArrayList<>();
                    forecastList.add(JSONObject.parseObject(forecastInfo.toString(), Forecast.class));
                    weather.setForecasts(forecastList);

                    // 获取天气更新时间
                    JSONObject updateInfo = heWeather.getJSONObject("update");
                    String updateTime = updateInfo.getString("loc");
                    weather.setUpdateTime(updateTime);

                    return weather;

                } catch (JSONException e) {
                    LOGGER.error("查询即时天气出错！", e);
                }
            }
        }
        return null;
    }

    // 将天气数据放入缓存
    private void setDataToRedis(String key, Weather weather) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, weather, REDIS_TIME_OUT, TimeUnit.SECONDS);
        LOGGER.info("************************ 将天气数据放入缓存 ************************");
    }
}
