package com.zq.controller;

import com.zq.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cache")
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @RequestMapping(value = "/set",method = RequestMethod.POST)
    public void set(@RequestParam String key,@RequestParam String value){
        cacheService.set(key,value);
    }
    @RequestMapping(value = "/setex",method = RequestMethod.POST)
    public void setex(@RequestParam String key, @RequestParam int seconds, @RequestParam String value){
        cacheService.setex(key,seconds,value);
    }

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public String get(@RequestParam String key){
       return cacheService.get(key);
    }
}
