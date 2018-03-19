package com.lpx.weather.controller;

import com.lpx.weather.model.Weather;
import com.lpx.weather.service.ICityDataService;
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
    @Autowired
    private ICityDataService cityDataService;

    // 查询未来三天的天气
    @GetMapping("future/{city}")
    public Weather getFutureWeather(@PathVariable String city) {
        return weatherService.getFutureWeather(city);
    }

    // 查询即时天气
    @GetMapping("current/{city}")
    public Weather getCurrentWeather(@PathVariable String city) {
        return weatherService.getCurrentWeather(city);
    }

    //查询天气（未来+即时）
    @GetMapping("{city}")
    public ModelAndView getWeatherReport(@PathVariable String city, Model model) {
        model.addAttribute("title", "天气预报");
        model.addAttribute("city", city);
        model.addAttribute("cityList", cityDataService.getCityList());
        model.addAttribute("future", getFutureWeather(city));
        model.addAttribute("current", getCurrentWeather(city));
        return new ModelAndView("weather/weather", "weatherData", model);
    }

}
