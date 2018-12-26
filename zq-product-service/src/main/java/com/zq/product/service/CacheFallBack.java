package com.zq.product.service;

import com.zq.product.utils.Response;
import org.springframework.stereotype.Component;

@Component
public class CacheFallBack implements CacheService {


    @Override
    public Response setCache(String key, String value) {
        return new Response().failure("网络异常!");
    }

    @Override
    public Response setexCache(String key, int seconds, String value) {
        return new Response().failure("网络异常!");
    }

    @Override
    public Response getCache(String key) {
        return new Response().failure("网络异常!");
    }
}
