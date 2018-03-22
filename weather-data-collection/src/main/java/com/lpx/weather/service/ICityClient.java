package com.lpx.weather.service;

import com.lpx.weather.model.City;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @Author: lipingxin
 * @Description:
 * @Date: 2018/3/22 15:07
 */

@FeignClient("city-data-api")
public interface ICityClient {

    @GetMapping("/city")
    List<City> getCityList();

}
