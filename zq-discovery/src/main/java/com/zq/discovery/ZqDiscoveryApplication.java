package com.zq.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer  //使用springcloud Eureka 作为注册中心
public class ZqDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZqDiscoveryApplication.class, args);
    }
}
