package com.zq.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan("com.zq.product.dao")
@EnableDiscoveryClient
@EnableFeignClients  //表示去注册中心发现服务
public class ZqProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZqProductServiceApplication.class, args);
    }
}
