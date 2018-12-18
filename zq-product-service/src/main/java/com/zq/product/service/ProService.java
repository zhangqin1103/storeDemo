package com.zq.product.service;

import com.github.pagehelper.Page;
import com.zq.product.entity.BuyCar;
import com.zq.product.entity.OrDer;
import com.zq.product.entity.Product;
import org.aspectj.weaver.ast.Or;

public interface ProService {
    Page<Product> selectAllPro(int pageIndex, int pageSize);
    //加入购物车
    void addToBuyCar(BuyCar buyCar);

    Page<BuyCar> getBuyCars(String userId,int pageIndex, int pageSize);

    String getProPrice(String proId,String proSpu);

    void addToOrder(OrDer orDer);

    void delFormBuyCar(String  id);

    Page<OrDer> getOrdersByUser (String buyerId,int pageIndex, int pageSize);

    OrDer getOrderDetail(String id);
}
