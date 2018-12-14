package com.zq.product.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.zq.product.entity.BuyCar;
import com.zq.product.entity.OrDer;
import com.zq.product.entity.Product;
import com.zq.product.service.ProService;
import com.zq.product.service.UserFeignService;
import com.zq.product.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ProController {
    @Autowired
    private ProService proService;
    @Autowired
    private UserFeignService userFeignService;

    @RequestMapping(value = "/getAllPro",method = RequestMethod.GET)
    public Response getAllPros(@RequestParam int pageIndex,@RequestParam int pageSize){
        try {
            Page<Product> result =  proService.selectAllPro(pageIndex,pageSize);
            return new Response().success(result);
        }catch (Exception e){
            e.printStackTrace();
            return new Response().failure(e.getMessage());
        }
    }

    @RequestMapping(value = "/addToBuyCar",method = RequestMethod.POST)
    public Response addToBuyCar(@RequestBody BuyCar buyCar){
        try {
            System.out.println(buyCar.getProductId());

            proService.addToBuyCar(buyCar);
            return new Response().success("加入购物车成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Response().failure(e.getMessage());
        }
    }

    @RequestMapping(value = "/getBuyCars",method = RequestMethod.GET)
    public Response getBuyCars(@RequestParam String userId,@RequestParam int pageIndex,@RequestParam int pageSize ){
        try {
            Page<BuyCar> page = proService.getBuyCars(userId,pageIndex,pageSize);
            return new Response().success(page);
        }catch (Exception e){
            e.printStackTrace();
            return new Response().failure(e.getMessage());
        }
    }

    /**
     * 使用feignclient 调用user服务
     * @param loginUser
     * @return
     */
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.GET)
    public Response getUserId(@RequestParam String loginUser){
        try {
            Response response = userFeignService.getUserInfo(loginUser);
            return response;
        }catch (Exception e){
            e.printStackTrace();
            return new Response().failure(e.getMessage());
        }
    }
    @RequestMapping(value = "/getProPrice",method = RequestMethod.GET)
    public Response getProPrice(@RequestParam Map<String,String> map){
        try {
            String proId = map.get("proId");
            String proSpu = map.get("proSpu");
            String price =  proService.getProPrice(proId,proSpu);
            return new Response().success(price);
        }catch (Exception e){
            e.printStackTrace();
            return new Response().failure(e.getMessage());
        }
    }
    @RequestMapping(value = "/addToOrder",method = RequestMethod.POST)
    public Response addToOrder(@RequestBody OrDer orDer){
        try {
            proService.addToOrder(orDer);
            return new Response().success();
        }catch (Exception e){
            e.printStackTrace();
            return new Response().failure(e.getMessage());
        }
    }

}
