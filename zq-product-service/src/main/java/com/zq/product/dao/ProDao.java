package com.zq.product.dao;

import com.github.pagehelper.Page;
import com.zq.product.entity.Product;

import java.util.Map;

public interface ProDao {
    int deleteByPrimaryKey(String id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    Page<Product> selectAllPro();

    String getProPrice(Map<String,String> map);
}