package com.lpx.weather;

import com.lpx.weather.model.Weather;
import com.lpx.weather.service.CityDataServiceImpl;
import com.lpx.weather.service.ICityDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.concurrent.TimeUnit;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WeatherApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ICityDataService cityDataService;

    @Test
    public void testSayHello() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Hello World!")));
    }

    @Test
    public void redisTest() {
        String key = "test-2018-03-16-21-43";
        long timeOut = 100L;
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, new Weather("2018-03-16 21:44:00"), timeOut, TimeUnit.SECONDS);

        Weather weather = (Weather) valueOperations.get(key);
        System.out.println("Redis中的数据：" + weather.getUpdateTime());
    }

    @Test
    public void cityDataServiceTest() {
        cityDataService.getCityList();
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Object dataInCache = valueOperations.get(CityDataServiceImpl.CITY_DATA_KEY);
        assert dataInCache != null;
    }

}
