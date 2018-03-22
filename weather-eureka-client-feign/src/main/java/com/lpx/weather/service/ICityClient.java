package com.lpx.weather.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: lipingxin
 * @Description:
 * @Date: 2018/3/22 14:12
 */

@FeignClient("city-data-api")
public interface ICityClient {

    @GetMapping("/city")
    String getCityList();
}
