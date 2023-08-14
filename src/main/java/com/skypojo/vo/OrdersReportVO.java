package com.skypojo.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName OrdersReportVO
 * @Author shuai
 * @create 2023/8/14 9:15
 * @Instruction
 */
public class OrdersReportVO implements Serializable {

    private String dateList;//日期列表
    private Double orderCompletionRate;//完成率
    private String orderCountList;//订单数列表
    private Integer totalOrderCount;//订单总数
    private Integer validOrderCount;//有效订单数
    private String validOrderCountList;//有效订单数列表
    public OrdersReportVO(){}

    public OrdersReportVO(String dateList, Double orderCompletionRate, String orderCountList, Integer totalOrderCount, Integer validOrderCount, String validOrderCountList) {
        this.dateList = dateList;
        this.orderCompletionRate = orderCompletionRate;
        this.orderCountList = orderCountList;
        this.totalOrderCount = totalOrderCount;
        this.validOrderCount = validOrderCount;
        this.validOrderCountList = validOrderCountList;
    }

    public String getDateList() {
        return dateList;
    }

    public void setDateList(String dateList) {
        this.dateList = dateList;
    }

    public Double getOrderCompletionRate() {
        return orderCompletionRate;
    }

    public void setOrderCompletionRate(Double orderCompletionRate) {
        this.orderCompletionRate = orderCompletionRate;
    }

    public String getOrderCountList() {
        return orderCountList;
    }

    public void setOrderCountList(String orderCountList) {
        this.orderCountList = orderCountList;
    }

    public Integer getTotalOrderCount() {
        return totalOrderCount;
    }

    public void setTotalOrderCount(Integer totalOrderCount) {
        this.totalOrderCount = totalOrderCount;
    }

    public Integer getValidOrderCount() {
        return validOrderCount;
    }

    public void setValidOrderCount(Integer validOrderCount) {
        this.validOrderCount = validOrderCount;
    }

    public String getValidOrderCountList() {
        return validOrderCountList;
    }

    public void setValidOrderCountList(String validOrderCountList) {
        this.validOrderCountList = validOrderCountList;
    }

    @Override
    public String toString() {
        return "OrdersReportVO{" +
                "dateList='" + dateList + '\'' +
                ", orderCompletionRate=" + orderCompletionRate +
                ", orderCountList='" + orderCountList + '\'' +
                ", totalOrderCount=" + totalOrderCount +
                ", validOrderCount=" + validOrderCount +
                ", validOrderCountList='" + validOrderCountList + '\'' +
                '}';
    }
}
