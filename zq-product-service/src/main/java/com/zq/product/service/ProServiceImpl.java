package com.zq.product.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.zq.product.dao.BuyCarDao;
import com.zq.product.dao.OrDerDao;
import com.zq.product.dao.ProDao;
import com.zq.product.entity.BuyCar;
import com.zq.product.entity.OrDer;
import com.zq.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProServiceImpl implements ProService {
    @Autowired
    private ProDao proDao;
    @Autowired
    private BuyCarDao buyCarDao;

    @Autowired
    private OrDerDao oDerDao;

    @Autowired
    private CacheServices cacheServices;


    @Override
    public Page<Product> selectAllPro(int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        Page<Product> res = proDao.selectAllPro();
        return res;
    }

    @Override
    public void addToBuyCar(BuyCar buyCar) {

       buyCarDao.insertBuyCar(buyCar);
    }

    @Override
    public Page<BuyCar> getBuyCars(String userId, int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        return buyCarDao.selectByUserId(userId);
    }

    /**
     * 根据商品id 商品属性查询 对应的商品单价
     * @param proId
     * @param proSpu
     * @return
     */
    @Override
    public Map<String,Object> getProPrice(String proId, String proSpu) {
        Gson gson = new Gson();
        String spu = "{" + proSpu + "}";
        HashMap queryMap = gson.fromJson(spu, HashMap.class);
        String size = queryMap.get("尺寸").toString();
        String model = queryMap.get("型号").toString();
        String color = queryMap.get("颜色").toString();
        queryMap.put("size",size);
        queryMap.put("model",model);
        queryMap.put("color",color);
        queryMap.put("proId",proId);
        return proDao.getProPrice(queryMap);
    }

    /**
     * 生成订单
     * @param orDer
     */
    @Override
    public void addToOrder(OrDer orDer) {

        oDerDao.addToOrder(orDer);
    }

    @Override
    public void delFormBuyCar(String id) {
       buyCarDao.delFormBuyCar(id);
    }

    @Override
    public Page<OrDer> getOrdersByUser(String buyerId,int pageIndex, int pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        return oDerDao.getAllOrders(buyerId);
    }

    @Override
    public OrDer getOrderDetail(String id) {
        return oDerDao.selectByPrimaryKey(id);
    }

    @Override
    public void delOrder(String id) {
        oDerDao.deleteByPrimaryKey(id);
    }

    @Override
    public void orderPay(String orderNo) {
        OrDer order = proDao.queryOrderByNo("select * from s_order where orderNo = '"+orderNo+"'");
        String orderDetail = "{" + order.getProDetail() +"}";
        HashMap detailMap = new Gson().fromJson(orderDetail,HashMap.class);
        Double d = Double.parseDouble(detailMap.get("购买数量").toString());
        Long buyNum = Math.round(d);
        String singleStock = cacheServices.getCache(order.getSpuId());
        Map<String,Object> updMap = new HashMap<>();
        updMap.put("proId",order.getProId());
        updMap.put("spuId",order.getSpuId());
        updMap.put("cacheStock",singleStock);
        updMap.put("buyNum",buyNum);
        proDao.updStock(updMap);
        proDao.updSingleStock(updMap);
        oDerDao.updPayStatu(orderNo);
    }
}
