package com.lpx.weather.controller;

import com.lpx.weather.service.IWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * description:
 * author: lpx
 * mail: lipingxin@outlook.com
 * time: 2018-03-14 23:19.
 */

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private IWeatherService weatherService;

    //查询天气（未来+即时）
    @GetMapping("{city}")
    public ModelAndView getWeatherReport(@PathVariable String city, Model model) {
        model.addAttribute("title", "天气预报");
        model.addAttribute("city", city);
        // TODO 调用城市数据微服务获取数据
//        model.addAttribute("cityList", cityDataService.getCityList());
        model.addAttribute("future", weatherService.getFutureWeather(city));
        model.addAttribute("current", weatherService.getCurrentWeather(city));
        return new ModelAndView("weather/weather", "weatherData", model);
    }

}
