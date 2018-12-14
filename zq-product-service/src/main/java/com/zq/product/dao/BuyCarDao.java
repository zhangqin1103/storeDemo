package com.zq.product.dao;

import com.github.pagehelper.Page;
import com.zq.product.entity.BuyCar;

public interface BuyCarDao {
    int deleteByPrimaryKey(String id);

    int insert(BuyCar record);
    int insertBuyCar(BuyCar record);
    Page<BuyCar> selectByUserId(String userId);
    int insertSelective(BuyCar record);

    BuyCar selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(BuyCar record);

    int updateByPrimaryKey(BuyCar record);
}