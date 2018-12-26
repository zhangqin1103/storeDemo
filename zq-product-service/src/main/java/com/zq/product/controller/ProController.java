package com.zq.product.controller;

import com.github.pagehelper.Page;
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

    /**
     * 商品列表
     * @param pageIndex
     * @param pageSize
     * @return
     */
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

    /**
     * 加入购物车
     * @param buyCar
     * @return
     */
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

    /**
     * 购物车数据
     * @param userId
     * @param pageIndex
     * @param pageSize
     * @return
     */
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
    //根据条件获取商品单价
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
    //添加至订单
    @RequestMapping(value = "/addToOrder",method = RequestMethod.POST)
    public Response addToOrder(@RequestBody OrDer orDer){
        try {
            proService.addToOrder(orDer);
            return new Response().success("添加成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Response().failure(e.getMessage());
        }
    }

    /**
     * 删除购物车数据
     * @param id
     * @return
     */
    @RequestMapping(value = "/delFormBuyCar/{id}",method = RequestMethod.DELETE)
    public Response delFormBuyCar(@PathVariable("id") String id){
        try {
            proService.delFormBuyCar(id);
            return new Response().success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Response().failure(e.getMessage());
        }
    }
    @RequestMapping(value = "/getOrders",method = RequestMethod.GET)
    public Response getOrders(@RequestParam String buyerId,@RequestParam  int pageIndex,@RequestParam int pageSize){
        try {
            return new Response().success(proService.getOrdersByUser(buyerId,pageIndex,pageSize));
        }catch (Exception e){
            e.printStackTrace();
            return new Response().failure(e.getMessage());
        }
    }

    @RequestMapping(value = "/getOrderDetail/{id}",method = RequestMethod.GET)
    public Response getOrderDetail(@PathVariable("id") String id){
        try {
            return new Response().success(proService.getOrderDetail(id));
        }catch (Exception e){
            e.printStackTrace();
            return new Response().failure(e.getMessage());
        }
    }


    @RequestMapping(value = "/delOrder/{id}",method = RequestMethod.DELETE)
    public Response delOrder(@PathVariable("id") String id){
        try {
            proService.delOrder(id);
            return new Response().success("删除成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Response().failure(e.getMessage());
        }
    }


}
