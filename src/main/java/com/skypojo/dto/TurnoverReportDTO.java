package com.skypojo.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName TurnoverReportDTO
 * @Author shuai
 * @create 2023/8/13 11:13
 * @Instruction 营业额dto
 */
public class TurnoverReportDTO implements Serializable {

    private String orderDate;
    private BigDecimal orderMoney;//对应mysql中decimal

    public TurnoverReportDTO(){}

    public TurnoverReportDTO(String orderDate, BigDecimal orderMoney) {
        this.orderDate = orderDate;
        this.orderMoney = orderMoney;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }
}
