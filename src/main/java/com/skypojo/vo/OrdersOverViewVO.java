package com.skypojo.vo;

import java.io.Serializable;

/**
 * @ClassName OrdersOverViewVO
 * @Author shuai
 * @create 2023/8/14 15:58
 * @Instruction
 */
public class OrdersOverViewVO implements Serializable {
    private Integer allOrders;//全部
    private Integer cancelledOrders;//已取消
    private Integer completedOrders;//已完成
    private Integer deliveredOrders;//待派送
    private Integer waitingOrders;//待接单

    public OrdersOverViewVO(){}

    public OrdersOverViewVO(Integer allOrders, Integer cancelledOrders, Integer completedOrders, Integer deliveredOrders, Integer waitingOrders) {
        this.allOrders = allOrders;
        this.cancelledOrders = cancelledOrders;
        this.completedOrders = completedOrders;
        this.deliveredOrders = deliveredOrders;
        this.waitingOrders = waitingOrders;
    }

    public Integer getAllOrders() {
        return allOrders;
    }

    public void setAllOrders(Integer allOrders) {
        this.allOrders = allOrders;
    }

    public Integer getCancelledOrders() {
        return cancelledOrders;
    }

    public void setCancelledOrders(Integer cancelledOrders) {
        this.cancelledOrders = cancelledOrders;
    }

    public Integer getCompletedOrders() {
        return completedOrders;
    }

    public void setCompletedOrders(Integer completedOrders) {
        this.completedOrders = completedOrders;
    }

    public Integer getDeliveredOrders() {
        return deliveredOrders;
    }

    public void setDeliveredOrders(Integer deliveredOrders) {
        this.deliveredOrders = deliveredOrders;
    }

    public Integer getWaitingOrders() {
        return waitingOrders;
    }

    public void setWaitingOrders(Integer waitingOrders) {
        this.waitingOrders = waitingOrders;
    }
}
