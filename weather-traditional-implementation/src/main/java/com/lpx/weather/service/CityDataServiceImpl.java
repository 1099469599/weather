package com.lpx.weather.service;

import com.lpx.weather.model.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * author: lpx
 * mail: lipingxin@outlook.com
 * time: 2018-03-16 23:39.
 */
@Service
public class CityDataServiceImpl implements ICityDataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityDataServiceImpl.class);
    public static final String CITY_DATA_KEY = "city-data-redis-key-";

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<City> getCityList() {
        // 查询缓存中是否有城市信息
        ValueOperations valueOperations = redisTemplate.opsForValue();
        List<City> cityList = null;
        Object dataInCache = valueOperations.get(CITY_DATA_KEY);
        if (dataInCache == null) {
            // 解析文件，并将城市信息放入redis
            String fileName = CityDataServiceImpl.class.getClassLoader().getResource("china-city-list.txt").getPath();
            LOGGER.info("############## 文件路径为 " + fileName);
            cityList = readFileByLines(fileName);
            if (!CollectionUtils.isEmpty(cityList)) {
                valueOperations.set(CITY_DATA_KEY, cityList);

            } else {
                throw new RuntimeException("读取文件失败，或城市数据文件为空");
            }

        } else {
            cityList = (List<City>) dataInCache;

        }

        return cityList;
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    private List<City> readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            List<City> cityList = new ArrayList<>();
            while ((tempString = reader.readLine()) != null) {
                String[] cityData = tempString.split("\\t");
                City city = new City();
                city.setId(cityData[0]);
                city.setLocation(cityData[2]);
                cityList.add(city);
            }

            reader.close();
            return cityList;

        } catch (IOException e) {
            //e.printStackTrace();
            LOGGER.error("读取城市文件失败", e);

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    LOGGER.error("流关闭失败", e1);
                }
            }
        }
        return null;
    }
}
