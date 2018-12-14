package com.zq.product.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author
 */
public class BuyCar implements Serializable {
    private String id;

    private String userId;

    private String productId;
    private String productName;
    private Long proNum;

    private String proSpu;

    private String status;

    private Date createTime;


    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getProNum() {
        return proNum;
    }

    public void setProNum(Long proNum) {
        this.proNum = proNum;
    }

    public String getProSpu() {
        return proSpu;
    }

    public void setProSpu(String proSpu) {
        this.proSpu = proSpu;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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
        BuyCar other = (BuyCar) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
                && (this.getProductId() == null ? other.getProductId() == null : this.getProductId().equals(other.getProductId()))
                && (this.getProNum() == null ? other.getProNum() == null : this.getProNum().equals(other.getProNum()))
                && (this.getProSpu() == null ? other.getProSpu() == null : this.getProSpu().equals(other.getProSpu()))
                && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getProductId() == null) ? 0 : getProductId().hashCode());
        result = prime * result + ((getProNum() == null) ? 0 : getProNum().hashCode());
        result = prime * result + ((getProSpu() == null) ? 0 : getProSpu().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", productId=").append(productId);
        sb.append(", proNum=").append(proNum);
        sb.append(", proSpu=").append(proSpu);
        sb.append(", status=").append(status);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}