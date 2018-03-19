package com.lpx.weather.job;

import com.lpx.weather.model.City;
import com.lpx.weather.service.ICityDataService;
import com.lpx.weather.service.IWeatherService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * description:
 * author: lpx
 * mail: lipingxin@outlook.com
 * time: 2018-03-16 22:20.
 */
public class WeatherDataSyncJob extends QuartzJobBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherDataSyncJob.class);

    @Autowired
    private ICityDataService cityDataService;
    @Autowired
    private IWeatherService weatherService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOGGER.info("\r\n------------  天气同步任务开始 ----------------\r\n");

        List<City> cityList = cityDataService.getCityList();
        if (!CollectionUtils.isEmpty(cityList)) {
            for (City city : cityList) {
                String cityId = city.getId();
                LOGGER.info("--- 开始缓存 【"+ cityId + "】 的数据");
                weatherService.syncWeatherData(cityId);
            }
        }

        LOGGER.info("\r\n------------  天气同步任务结束 ----------------\r\n");
    }
}
