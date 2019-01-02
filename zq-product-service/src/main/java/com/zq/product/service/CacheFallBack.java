package com.zq.product.service;

import org.springframework.stereotype.Component;

@Component
public class CacheFallBack implements CacheServices {


    @Override
    public String setCache(String key, String value) {
        return "网络异常!";
    }

    @Override
    public String setexCache(String key, int seconds, String value) {
        return "网络异常!";
    }

    @Override
    public String getCache(String key) {
         return "网络异常!";
    }

    @Override
    public Long decrBykey(String key, Long increament) {
        return  (long)-1;
    }
}
