package com.zq.product.service;

import com.zq.product.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "zq-redis-service",fallback = CacheFallBack.class )
public interface CacheServices  {

    @RequestMapping(value = "/cache/set",method = RequestMethod.GET)
    public Response setCache(@RequestParam("key") String key,@RequestParam("value") String value);

    @RequestMapping(value = "/cache/setex",method = RequestMethod.GET)
    public Response setexCache(@RequestParam("key") String key,@RequestParam("seconds") int seconds,@RequestParam("value") String value);

    @RequestMapping(value = "/cache/get",method = RequestMethod.GET)
    public Response getCache(@RequestParam("key") String key);
}
