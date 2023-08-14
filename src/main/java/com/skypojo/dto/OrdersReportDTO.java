package com.skypojo.dto;

import java.io.Serializable;

/**
 * @ClassName OrdersReportDTO
 * @Author shuai
 * @create 2023/8/14 9:34
 * @Instruction
 */
public class OrdersReportDTO implements Serializable {

    private String orderTime;//下单时间
    private Integer orderCount;//订单数量
    public OrdersReportDTO(){}

    public OrdersReportDTO(String orderTime, Integer orderCount) {
        this.orderTime = orderTime;
        this.orderCount = orderCount;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }
}
