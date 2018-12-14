package com.zq.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
//EnableZuulServer的增强版，当Zuul与Eureka、Ribbon等组件配合使用时用@EnableZuulProxy。
@EnableZuulProxy
public class ZqZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZqZuulApplication.class, args);
    }
}
