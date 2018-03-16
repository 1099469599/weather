package com.lpx.weather.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * description:
 * author: lpx
 * mail: lipingxin@outlook.com
 * time: 2018-03-14 22:07.
 */
public class Weather implements Serializable {

    @Getter @Setter private City city;
    @Getter @Setter private String updateTime;
    @Getter @Setter private List<Forecast> forecasts;

    public Weather() {
    }

    public Weather(String updateTime) {
        this.updateTime = updateTime;
    }
}
