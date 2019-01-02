package com.zq.product.dao;

import com.github.pagehelper.Page;
import com.zq.product.entity.OrDer;
import com.zq.product.entity.Product;

import java.util.Map;

public interface ProDao {
    int deleteByPrimaryKey(String id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    int updStock(Map<String,Object> map);

    int updSingleStock(Map<String,Object> map);

    Page<Product> selectAllPro();

    Map<String,Object> getProPrice(Map<String,String> map);

    OrDer  queryOrderByNo(String sql);
    String query(String sql);
}