package com.lpx.weather.controller;

import com.lpx.weather.service.ICityClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: lipingxin
 * @Description:
 * @Date: 2018/3/22 14:14
 */

@RestController
@RequestMapping("city")
public class CityController {

    @Autowired
    private ICityClient cityClient;

    @GetMapping("")
    public String getCityList() {
        String cityList = cityClient.getCityList();
        return cityList;
    }

}
