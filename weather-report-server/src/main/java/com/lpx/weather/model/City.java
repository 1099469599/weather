package com.lpx.weather.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * description: 城市
 * author: lpx
 * mail: lipingxin@outlook.com
 * time: 2018-03-14 22:15.
 */
public class City implements Serializable {

    //@JSONField(name="cid")
    @Getter @Setter private String id;
    @Getter @Setter private String location;
    @Getter @Setter private String parentCity;
    @Getter @Setter private String adminArea;
    @Getter @Setter private String lat;
    @Getter @Setter private String lon;

}
