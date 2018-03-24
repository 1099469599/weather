package com.lpx.weather.service;

import com.lpx.weather.model.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.*;
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

    @Value("${city.data.location}")
    private String filePath;

    @Override
    public List<City> getCityList() {
        // 查询缓存中是否有城市信息
        ValueOperations valueOperations = redisTemplate.opsForValue();
        List<City> cityList = null;
        Object dataInCache = valueOperations.get(CITY_DATA_KEY);
        if (dataInCache == null) {
            // 解析文件，并将城市信息放入redis
//            String fileName = CityDataServiceImpl.class.getClassLoader().getResource("china-city-list.txt").getPath();
            // 在IDEA中运行时，上面的代码是没问题的，但是打包成jar后，无法读取文件，所以写死路径
//            String fileName = "D://china-city-list.txt";
            LOGGER.info("############## 文件路径为 " + filePath);
            cityList = readFileByLines(filePath);
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
            // 注意可能发生的乱码问题。注意文件编码
//            reader = new BufferedReader(new FileReader(file));
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8"));
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
