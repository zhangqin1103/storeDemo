package com.zq.controller;

import com.zq.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @RequestMapping("/set")
    public void set(HttpServletRequest request,String key, String value){
        cacheService.set(key,value);
    }
    @RequestMapping("/setex")
    public void setex(String key,int seconds,String value){
        cacheService.setex(key,seconds,value);
    }

    @RequestMapping("/get")
    public String get(String key){
       return cacheService.get(key);
    }
}
