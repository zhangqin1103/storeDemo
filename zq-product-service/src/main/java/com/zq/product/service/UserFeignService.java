package com.zq.product.service;

import com.zq.product.utils.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 使用feignclient 调用user服务
 */
@FeignClient(value = "zq-user-service",fallback = UserInfoFallBack.class )
public interface UserFeignService {

    @RequestMapping(value = "/getUserInfo",method = RequestMethod.GET)
    public Response getUserInfo(@RequestParam(value = "loginUser") String loginUser);
}
