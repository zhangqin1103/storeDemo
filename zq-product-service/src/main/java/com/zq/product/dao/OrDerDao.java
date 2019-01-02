package com.zq.product.dao;

import com.github.pagehelper.Page;
import com.zq.product.entity.OrDer;

public interface OrDerDao {
    int deleteByPrimaryKey(String id);

    int insert(OrDer record);

    int insertSelective(OrDer record);

    OrDer selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrDer record);

    int updPayStatu(String orderNo);

    void addToOrder(OrDer orDer);

    Page<OrDer> getAllOrders(String buyerId);
}
