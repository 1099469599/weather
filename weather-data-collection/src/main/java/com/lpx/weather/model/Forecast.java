package com.lpx.weather.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * description: 天气数据
 * author: lpx
 * mail: lipingxin@outlook.com
 * time: 2018-03-14 22:18.
 */
public class Forecast implements Serializable {

    @Getter @Setter private String date; //预报日期
    @Getter @Setter private String ss; //日落时间
    @Getter @Setter private String sr; //日出时间
    @Getter @Setter private String mr; //月升时间
    @Getter @Setter private String ms; //月落时间
    @Getter @Setter private String tmpMax; // 最高温度
    @Getter @Setter private String tmpMin; // 最低温度
    @Getter @Setter private String condCodeD; // 白天天气状况代码
    @Getter @Setter private String condCodeN; // 晚间天气状况代码
    @Getter @Setter private String condTxtD; // 白天天气状况描述
    @Getter @Setter private String condTxtN; // 晚间天气状况描述
    @Getter @Setter private String windDeg; // 风向360角度
    @Getter @Setter private String windDir; // 风向
    @Getter @Setter private String windSc; // 风力
    @Getter @Setter private String windSpd; // 风速，公里/小时
    @Getter @Setter private String hum; // 相对湿度
    @Getter @Setter private String pcpn; // 降水量
    @Getter @Setter private String pop; // 降水概率
    @Getter @Setter private String pres; // 大气压强
    @Getter @Setter private String uvIndex; // 紫外线强度指数
    @Getter @Setter private String vis; // 能见度，单位：公里

    @Getter @Setter private String fl; // 体感温度，默认单位：摄氏度
    @Getter @Setter private String tmp; // 温度，默认单位：摄氏度
    @Getter @Setter private String condCode; // 实况天气状况代码
    @Getter @Setter private String condTxt; // 实况天气状况代码
    @Getter @Setter private String cloud; // 云量

}
