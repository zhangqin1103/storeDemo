package com.zq.product.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class OrDer implements Serializable {
    /**
     * 主键id
     */
    private String id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 买家id
     */
    private String buyerId;
    /**
     * 商品明细
     */
    private String proDetail;

    /**
     * 交易状态
     */
    private String tradeStatus;

    /**
     * 支付状态
     */
    private String payStatus;

    /**
     * 订单金额
     */
    private Long oderAmount;

    private String proId;

    private String buyCarId;

    private String spuId;
    /**
     * 创建时间
     */
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    // @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 收货地址
     */
    private String placeAddress;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public Long getOderAmount() {
        return oderAmount;
    }

    public void setOderAmount(Long oderAmount) {
        this.oderAmount = oderAmount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
    }

    public String getProDetail() {
        return proDetail;
    }

    public void setProDetail(String proDetail) {
        this.proDetail = proDetail;
    }

    public String getBuyCarId() {
        return buyCarId;
    }

    public void setBuyCarId(String buyCarId) {
        this.buyCarId = buyCarId;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getSpuId() {
        return spuId;
    }

    public void setSpuId(String spuId) {
        this.spuId = spuId;
    }

    @Override
    public String toString() {
        return "OrDer{" +
                "id='" + id + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", buyerId='" + buyerId + '\'' +
                ", proDetail='" + proDetail + '\'' +
                ", tradeStatus='" + tradeStatus + '\'' +
                ", payStatus='" + payStatus + '\'' +
                ", oderAmount=" + oderAmount +
                ", proId='" + proId + '\'' +
                ", buyCarId='" + buyCarId + '\'' +
                ", spuId='" + spuId + '\'' +
                ", createTime=" + createTime +
                ", placeAddress='" + placeAddress + '\'' +
                '}';
    }
}