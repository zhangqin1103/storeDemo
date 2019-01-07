package com.zq.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.zq.user.dao")
@EnableEurekaClient
public class ZqUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZqUserServiceApplication.class, args);
    }
}
