package com.zq.product.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class Product implements Serializable {
    /**
     * 主键
     */
    private String id;

    /**
     * 商品名称
     */
    private String proName;

    /**
     * 商品库存
     */
    private Integer proStock;

    private String imgUrl;

    /**
     * 商品类型
     */
    private String proType;

    /**
     * 上架时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss") //返回参数时
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")  //传入参数时
    private Date putAwayTime;

    /**
     * 状态
     */
    private Integer proStatus;

    /**
     * 商品类型id
     */
    private String proTypeId;
    private String priceZone;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Integer getProStock() {
        return proStock;
    }

    public void setProStock(Integer proStock) {
        this.proStock = proStock;
    }

    public String getProType() {
        return proType;
    }

    public String getPriceZone() {
        return priceZone;
    }

    public void setPriceZone(String priceZone) {
        this.priceZone = priceZone;
    }

    public void setProType(String proType) {
        this.proType = proType;
    }

    public Date getPutAwayTime() {
        return putAwayTime;
    }

    public void setPutAwayTime(Date putAwayTime) {
        this.putAwayTime = putAwayTime;
    }

    public Integer getProStatus() {
        return proStatus;
    }

    public void setProStatus(Integer proStatus) {
        this.proStatus = proStatus;
    }

    public String getProTypeId() {
        return proTypeId;
    }

    public void setProTypeId(String proTypeId) {
        this.proTypeId = proTypeId;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Product other = (Product) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getProName() == null ? other.getProName() == null : this.getProName().equals(other.getProName()))
            && (this.getProStock() == null ? other.getProStock() == null : this.getProStock().equals(other.getProStock()))
            && (this.getProType() == null ? other.getProType() == null : this.getProType().equals(other.getProType()))
            && (this.getPutAwayTime() == null ? other.getPutAwayTime() == null : this.getPutAwayTime().equals(other.getPutAwayTime()))
            && (this.getProStatus() == null ? other.getProStatus() == null : this.getProStatus().equals(other.getProStatus()))
            && (this.getProTypeId() == null ? other.getProTypeId() == null : this.getProTypeId().equals(other.getProTypeId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getProName() == null) ? 0 : getProName().hashCode());
        result = prime * result + ((getProStock() == null) ? 0 : getProStock().hashCode());
        result = prime * result + ((getProType() == null) ? 0 : getProType().hashCode());
        result = prime * result + ((getPutAwayTime() == null) ? 0 : getPutAwayTime().hashCode());
        result = prime * result + ((getProStatus() == null) ? 0 : getProStatus().hashCode());
        result = prime * result + ((getProTypeId() == null) ? 0 : getProTypeId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", proName=").append(proName);
        sb.append(", proStock=").append(proStock);
        sb.append(", proType=").append(proType);
        sb.append(", putAwayTime=").append(putAwayTime);
        sb.append(", proStatus=").append(proStatus);
        sb.append(", proTypeId=").append(proTypeId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}