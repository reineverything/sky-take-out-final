package com.skypojo.vo;

import java.io.Serializable;

/**
 * @ClassName BusinessDataVO
 * @Author shuai
 * @create 2023/8/14 14:41
 * @Instruction 概览数据类
 */
public class BusinessDataVO implements Serializable {

    private Integer newUsers;
    private Double orderCompletionRate;//订单完成率
    private Double turnover;//营业额
    private Double unitPrice;//平均客单价
    private Integer validOrderCount;//有效订单数
    public BusinessDataVO(){}

    public BusinessDataVO(Integer newUsers, Double orderCompletionRate, Double turnover, Double unitPrice, Integer validOrderCount) {
        this.newUsers = newUsers;
        this.orderCompletionRate = orderCompletionRate;
        this.turnover = turnover;
        this.unitPrice = unitPrice;
        this.validOrderCount = validOrderCount;
    }

    public Integer getNewUsers() {
        return newUsers;
    }

    public void setNewUsers(Integer newUsers) {
        this.newUsers = newUsers;
    }

    public Double getOrderCompletionRate() {
        return orderCompletionRate;
    }

    public void setOrderCompletionRate(Double orderCompletionRate) {
        this.orderCompletionRate = orderCompletionRate;
    }

    public Double getTurnover() {
        return turnover;
    }

    public void setTurnover(Double turnover) {
        this.turnover = turnover;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getValidOrderCount() {
        return validOrderCount;
    }

    public void setValidOrderCount(Integer validOrderCount) {
        this.validOrderCount = validOrderCount;
    }
}
