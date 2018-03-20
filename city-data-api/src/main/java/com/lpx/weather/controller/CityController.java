package com.lpx.weather.controller;

import com.lpx.weather.model.City;
import com.lpx.weather.service.ICityDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: lipingxin
 * @Description:
 * @Date: 2018/3/20 14:30
 */
@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private ICityDataService cityDataService;

    @GetMapping
    public List<City> getCityList() {
        return cityDataService.getCityList();
    }

}
