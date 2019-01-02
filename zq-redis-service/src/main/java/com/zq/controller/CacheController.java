package com.zq.controller;

import com.zq.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @RequestMapping(value = "/set",method = RequestMethod.GET)
    public void set(String key,String value){
        cacheService.set(key,value);
    }
    @RequestMapping(value = "/setex",method = RequestMethod.GET)
    public void setex(String key,int seconds,String value){
        cacheService.setex(key,seconds,value);
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public String get(String key){
       return cacheService.get(key);
    }

    @RequestMapping(value = "/decrBykey",method = RequestMethod.GET)
    public Long decrBykey(String key,Long increament){
        return cacheService.decrBy(key,increament);
    }

}
