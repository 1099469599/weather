## 简单的天气预报项目

1. **简介**

&emsp;&emsp;最近在跟着【从天气项目看Spring Cloud微服务治理】学springboot、springcloud，跟着老师自己敲了一个项目。
跟老师的代码不完全一样，差别还是挺多的。

2. **主要技术**
  - SpringBoot 2.0 RELEASE 
  - SpringCloud Finchley.M8 
  - Redis(Lettuce) 
  - quartz 
  - ...... 

3. **子项目说明**

  - weather-traditional-implementation 传统实现方式
  - city-data-api 城市数据查询模块
  - weather-data-collection 天气数据采集模块，调用city-data-api
  - weather-data-api 天气数据查询模块
  - weather-report-server 天气预报模块，调用city-data-api 和 weather-data-api
  - weather-eureka-server
  - weather-eureka-client 普通eureka客户端
  - weather-eureka-client-feign 集成feign的eureka客户端
