package com.zq.product.service;

import com.zq.product.utils.Response;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * feign服务降低类
 */
@Component
public class UserInfoFallBack implements UserFeignService  {


    @Override
    public Response getUserInfo(String loginUser) {
        String msg =  "网络异常,获取用户信息失败" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss"));
        return  new Response().failure(msg);
    }
}
