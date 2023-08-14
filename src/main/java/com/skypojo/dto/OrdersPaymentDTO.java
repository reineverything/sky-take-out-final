package com.skypojo.dto;

/**
 * @ClassName OrdersPaymentDTO
 * @Author shuai
 * @create 2023/8/10 15:48
 * @Instruction 订单支付
 */
public class OrdersPaymentDTO {

    private String orderNumber;
    private Integer payMethod;

    public OrdersPaymentDTO(){}

    public OrdersPaymentDTO(String orderNumber, Integer payMethod) {
        this.orderNumber = orderNumber;
        this.payMethod = payMethod;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    @Override
    public String toString() {
        return "OrdersPaymentDTO{" +
                "orderNumber='" + orderNumber + '\'' +
                ", payMethod='" + payMethod + '\'' +
                '}';
    }
}
