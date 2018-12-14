package com.zq.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.zq.user.dao")
@EnableDiscoveryClient
public class ZqUserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZqUserServiceApplication.class, args);
    }
}
