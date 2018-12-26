package com.zq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ZqRedisServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZqRedisServiceApplication.class, args);
    }


}

