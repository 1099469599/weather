## 简单的天气预报项目

1. **简介**

&emsp;&emsp;最近在跟着【[从天气项目看Spring Cloud微服务治理](https://coding.imooc.com/class/187.html)】学springboot、springcloud，跟着老师自己敲了一个项目。
跟老师的代码不完全一样，差别还是挺多的。

2. **主要技术**
  * [SpringBoot 2.0 RELEASE](https://projects.spring.io/spring-boot/)
  * [SpringCloud Finchley.M8](http://projects.spring.io/spring-cloud/)
  * [Redis(Lettuce)](https://redis.io/)
  * [quartz](http://www.quartz-scheduler.org/)
  * ...... 

3. **子项目说明**

  - weather-traditional-implementation 传统实现方式
  - city-data-api 城市数据查询模块
  - weather-data-collection 天气数据采集模块，调用city-data-api
  - weather-data-api 天气数据查询模块
  - weather-report-server 天气预报模块，直接调用city-data-api 和 weather-data-api
  - weather-report-server-gateway 天气预报模块，通过zuul调用city-data-api 和 weather-data-api
  - weather-eureka-server 注册中心
  - weather-eureka-client 普通eureka客户端
  - weather-eureka-client-feign 集成feign的eureka客户端
  - weather-eureka-client-zuul 集成zuul、feign的eureka客户端

4. **所用接口**
 
 - 天气接口：[和风天气](https://www.heweather.com/documents/api/s6)
 - 城市数据：[和风天气](https://www.heweather.com/documents/city)，对数据做了截取，只取用了一部分
