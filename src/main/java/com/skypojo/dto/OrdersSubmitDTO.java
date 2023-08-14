package com.skypojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 用户生成订单
 */

public class OrdersSubmitDTO implements Serializable {
    //地址簿id
    private Long addressBookId;
    //付款方式
    private int payMethod;
    //备注
    private String remark;
    //预计送达时间
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime estimatedDeliveryTime;
    //配送状态  1立即送出  0选择具体时间
    private Integer deliveryStatus;
    //餐具数量
    private Integer tablewareNumber;
    //餐具数量状态  1按餐量提供  0选择具体数量
    private Integer tablewareStatus;
    //打包费
    private Integer packAmount;
    //总金额
    private BigDecimal amount;

    public OrdersSubmitDTO(){}

    public OrdersSubmitDTO(Long addressBookId, int payMethod, String remark, LocalDateTime estimatedDeliveryTime, Integer deliveryStatus, Integer tablewareNumber, Integer tablewareStatus, Integer packAmount, BigDecimal amount) {
        this.addressBookId = addressBookId;
        this.payMethod = payMethod;
        this.remark = remark;
        this.estimatedDeliveryTime = estimatedDeliveryTime;
        this.deliveryStatus = deliveryStatus;
        this.tablewareNumber = tablewareNumber;
        this.tablewareStatus = tablewareStatus;
        this.packAmount = packAmount;
        this.amount = amount;
    }

    public Long getAddressBookId() {
        return addressBookId;
    }

    public void setAddressBookId(Long addressBookId) {
        this.addressBookId = addressBookId;
    }

    public int getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(int payMethod) {
        this.payMethod = payMethod;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public LocalDateTime getEstimatedDeliveryTime() {
        return estimatedDeliveryTime;
    }

    public void setEstimatedDeliveryTime(LocalDateTime estimatedDeliveryTime) {
        this.estimatedDeliveryTime = estimatedDeliveryTime;
    }

    public Integer getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(Integer deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Integer getTablewareNumber() {
        return tablewareNumber;
    }

    public void setTablewareNumber(Integer tablewareNumber) {
        this.tablewareNumber = tablewareNumber;
    }

    public Integer getTablewareStatus() {
        return tablewareStatus;
    }

    public void setTablewareStatus(Integer tablewareStatus) {
        this.tablewareStatus = tablewareStatus;
    }

    public Integer getPackAmount() {
        return packAmount;
    }

    public void setPackAmount(Integer packAmount) {
        this.packAmount = packAmount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OrdersSubmitDTO{" +
                "addressBookId=" + addressBookId +
                ", payMethod=" + payMethod +
                ", remark='" + remark + '\'' +
                ", estimatedDeliveryTime=" + estimatedDeliveryTime +
                ", deliveryStatus=" + deliveryStatus +
                ", tablewareNumber=" + tablewareNumber +
                ", tablewareStatus=" + tablewareStatus +
                ", packAmount=" + packAmount +
                ", amount=" + amount +
                '}';
    }
}
