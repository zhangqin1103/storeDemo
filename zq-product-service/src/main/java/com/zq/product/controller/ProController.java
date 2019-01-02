package com.zq.product.controller;

import com.github.pagehelper.Page;
import com.google.gson.Gson;
import com.zq.product.entity.BuyCar;
import com.zq.product.entity.OrDer;
import com.zq.product.entity.Product;
import com.zq.product.service.CacheServices;
import com.zq.product.service.ProService;
import com.zq.product.service.UserFeignService;
import com.zq.product.utils.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ProController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProController.class);

    @Autowired
    private ProService proService;
    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private CacheServices cacheServices;

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
            String spu = buyCar.getProSpu();
            spu = spu.replace("{"," ").replace("}"," ");
            buyCar.setProSpu(spu);
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
    //根据条件获取商品单价和库存
    @RequestMapping(value = "/getProPrice",method = RequestMethod.GET)
    public Response getProPrice(@RequestParam Map<String,String> map){
        try {
            String proId = map.get("proId");
            String proSpu = map.get("proSpu");
            Map<String,Object> resMap =  proService.getProPrice(proId,proSpu);
            // 下单时将商品库存放入redis 缓存 key 值为商品属性id
            String stock = resMap.get("singleStock").toString();
            String spuId = resMap.get("id").toString();
            Long _stock = Long.valueOf(stock);
            if(_stock >= 1){
                cacheServices.setCache(spuId,String.valueOf(resMap.get("singleStock")));
                return new Response().success(resMap);
            }else{
                return new Response().failure("对不起,该商品已经售罄!");
            }
        }catch (Exception e){
            e.printStackTrace();
            return new Response().failure(e.getMessage());
        }
    }
    //添加至订单
    @RequestMapping(value = "/addToOrder",method = RequestMethod.POST)
    public Response addToOrder(@RequestBody OrDer orDer){
        try {
            String orderDetail = orDer.getProDetail();
            String orderDetail1 = orderDetail.replace("{"," ").replace("}"," ").replace("\""," ");
            orDer.setProDetail(orderDetail1);

            //从订单明细中获取购买数量
            HashMap detailMap = new Gson().fromJson(orderDetail,HashMap.class);
            Double d = Double.parseDouble(detailMap.get("购买数量").toString());
            Long increament = Math.round(d);
            // 预占库存
            Long stock =  cacheServices.decrBykey(orDer.getSpuId(),increament);
            if(stock>=1){
                proService.addToOrder(orDer);
                // 订单添加成功之后
                // 删除购物车
                proService.delFormBuyCar(orDer.getBuyCarId());
                return new Response().success("添加成功!");
            }else{
                return new Response().failure("库存不足!");
            }
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

    /**
     * 支付后修改订单相关状态
     * @param
     * @return
     */
    @RequestMapping(value = "/realPay/{orderNo}",method = RequestMethod.POST)
    public Response realPay(@PathVariable("orderNo") String orderNo){
        try {
            proService.orderPay(orderNo);
            return new Response().success("支付成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Response().failure(e.getMessage());
        }
    }



}
