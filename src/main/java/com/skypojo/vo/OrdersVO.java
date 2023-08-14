package com.skypojo.vo;

import com.skypojo.entity.OrderDetail;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OrdersVO
 * @Author shuai
 * @create 2023/8/8 10:51
 * @Instruction 订单分页条件查询返回结果
 */
public class OrdersVO implements Serializable {
    private Long id;

    //订单号
    private String number;

    //订单状态 1待付款 2待接单 3已接单 4派送中 5已完成 6已取消 7退款
    private Integer status;

    //下单用户id
    private Long userId;

    //地址id
    private Long addressBookId;

    //下单时间
    private LocalDateTime orderTime;

    //结账时间
    private LocalDateTime checkoutTime;

    //支付方式 1微信，2支付宝
    private Integer payMethod;

    //支付状态 0未支付 1已支付 2退款
    private Integer payStatus;

    //实收金额
    private BigDecimal amount;

    //备注
    private String remark;

    //用户名
    private String userName;

    //手机号
    private String phone;

    //地址
    private String address;

    //收货人
    private String consignee;

    //订单取消原因
    private String cancelReason;

    //订单拒绝原因
    private String rejectionReason;

    //订单取消时间
    private LocalDateTime cancelTime;

    //预计送达时间
    private LocalDateTime estimatedDeliveryTime;

    //配送状态  1立即送出  0选择具体时间
    private Integer deliveryStatus;

    //送达时间
    private LocalDateTime deliveryTime;

    //打包费
    private int packAmount;

    //餐具数量
    private int tablewareNumber;

    //餐具数量状态  1按餐量提供  0选择具体数量
    private Integer tablewareStatus;

    //包含的菜品
    private String orderDishes;

    //订单详情
    private List<OrderDetail> orderDetailList=new ArrayList<>();

    public OrdersVO(){}

    public OrdersVO(Long id, String number, Integer status, Long userId, Long addressBookId, LocalDateTime orderTime, LocalDateTime checkoutTime, Integer payMethod, Integer payStatus, BigDecimal amount, String remark, String userName, String phone, String address, String consignee, String cancelReason, String rejectionReason, LocalDateTime cancelTime, LocalDateTime estimatedDeliveryTime, Integer deliveryStatus, LocalDateTime deliveryTime, int packAmount, int tablewareNumber, Integer tablewareStatus, String orderDishes, List<OrderDetail> orderDetailList) {
        this.id = id;
        this.number = number;
        this.status = status;
        this.userId = userId;
        this.addressBookId = addressBookId;
        this.orderTime = orderTime;
        this.checkoutTime = checkoutTime;
        this.payMethod = payMethod;
        this.payStatus = payStatus;
        this.amount = amount;
        this.remark = remark;
        this.userName = userName;
        this.phone = phone;
        this.address = address;
        this.consignee = consignee;
        this.cancelReason = cancelReason;
        this.rejectionReason = rejectionReason;
        this.cancelTime = cancelTime;
        this.estimatedDeliveryTime = estimatedDeliveryTime;
        this.deliveryStatus = deliveryStatus;
        this.deliveryTime = deliveryTime;
        this.packAmount = packAmount;
        this.tablewareNumber = tablewareNumber;
        this.tablewareStatus = tablewareStatus;
        this.orderDishes = orderDishes;
        this.orderDetailList = orderDetailList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAddressBookId() {
        return addressBookId;
    }

    public void setAddressBookId(Long addressBookId) {
        this.addressBookId = addressBookId;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public LocalDateTime getCheckoutTime() {
        return checkoutTime;
    }

    public void setCheckoutTime(LocalDateTime checkoutTime) {
        this.checkoutTime = checkoutTime;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public LocalDateTime getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(LocalDateTime cancelTime) {
        this.cancelTime = cancelTime;
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

    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public int getPackAmount() {
        return packAmount;
    }

    public void setPackAmount(int packAmount) {
        this.packAmount = packAmount;
    }

    public int getTablewareNumber() {
        return tablewareNumber;
    }

    public void setTablewareNumber(int tablewareNumber) {
        this.tablewareNumber = tablewareNumber;
    }

    public Integer getTablewareStatus() {
        return tablewareStatus;
    }

    public void setTablewareStatus(Integer tablewareStatus) {
        this.tablewareStatus = tablewareStatus;
    }

    public String getOrderDishes() {
        return orderDishes;
    }

    public void setOrderDishes(String orderDishes) {
        this.orderDishes = orderDishes;
    }

    public List<OrderDetail> getOrderDetailList() {
        return orderDetailList;
    }

    public void setOrderDetailList(List<OrderDetail> orderDetailList) {
        this.orderDetailList = orderDetailList;
    }

    @Override
    public String toString() {
        return "OrdersVO{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", status=" + status +
                ", userId=" + userId +
                ", addressBookId=" + addressBookId +
                ", orderTime=" + orderTime +
                ", checkoutTime=" + checkoutTime +
                ", payMethod=" + payMethod +
                ", payStatus=" + payStatus +
                ", amount=" + amount +
                ", remark='" + remark + '\'' +
                ", userName='" + userName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", consignee='" + consignee + '\'' +
                ", cancelReason='" + cancelReason + '\'' +
                ", rejectionReason='" + rejectionReason + '\'' +
                ", cancelTime=" + cancelTime +
                ", estimatedDeliveryTime=" + estimatedDeliveryTime +
                ", deliveryStatus=" + deliveryStatus +
                ", deliveryTime=" + deliveryTime +
                ", packAmount=" + packAmount +
                ", tablewareNumber=" + tablewareNumber +
                ", tablewareStatus=" + tablewareStatus +
                ", orderDishes='" + orderDishes + '\'' +
                ", orderDetailList=" + orderDetailList +
                '}';
    }
}
