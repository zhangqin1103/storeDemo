package com.zq.product.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.gson.Gson;
import com.zq.product.dao.BuyCarDao;
import com.zq.product.dao.ProDao;
import com.zq.product.entity.BuyCar;
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
    public String getProPrice(String proId, String proSpu) {
        Gson gson = new Gson();
        HashMap queryMap = gson.fromJson(proSpu, HashMap.class);
        queryMap.put("proId",proId);
        return proDao.getProPrice(queryMap);
    }
}
