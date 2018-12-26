package com.zq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ZqRedisServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZqRedisServiceApplication.class, args);
    }


}

