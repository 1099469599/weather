package com.lpx.weather.job;

import org.quartz.*;
import org.springframework.context.annotation.Bean;

/**
 * description:
 * author: lpx
 * mail: lipingxin@outlook.com
 * time: 2018-03-16 22:20.
 */
//@Configuration
public class QuartzConfig {

    private static final int TIME = 300;

    @Bean
    public JobDetail weatherDataSyncJobDetail() {
        return JobBuilder.newJob(WeatherDataSyncJob.class)
                .withIdentity("Weather Data Sync Job Detail")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger weatherDataSyncTrigger() {

        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(TIME).repeatForever();

        return TriggerBuilder.newTrigger()
                .forJob(weatherDataSyncJobDetail())
                .withIdentity("Weather Data Sync Job Trigger")
                .withSchedule(scheduleBuilder)
                .build();
    }

}
