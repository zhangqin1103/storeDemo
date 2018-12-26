package com.zq.product.service;

import com.zq.product.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "zq-redis-service",fallback = CacheFallBack.class )
public interface CacheService  {

    @RequestMapping(value = "/set",method = RequestMethod.POST)
    public Response setCache(@RequestParam String key,@RequestParam String value);

    @RequestMapping(value = "/setex",method = RequestMethod.POST)
    public Response setexCache(@RequestParam String key,@RequestParam int seconds,@RequestParam String value);

    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public Response getCache(@RequestParam String key);
}
