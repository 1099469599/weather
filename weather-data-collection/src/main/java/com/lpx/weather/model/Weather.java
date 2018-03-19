package com.lpx.weather.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

/**
 * description:
 * author: lpx
 * mail: lipingxin@outlook.com
 * time: 2018-03-14 22:07.
 */
@NoArgsConstructor
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class Weather implements Serializable {

    @Getter @Setter private City city;
    @NonNull @Getter @Setter private String updateTime;
    @Getter @Setter private List<Forecast> forecasts;

}
