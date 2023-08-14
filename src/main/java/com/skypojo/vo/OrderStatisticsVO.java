package com.skypojo.vo;

import lombok.Builder;

import java.io.Serializable;

/**
 * @ClassName OrderStatisticsVO
 * @Author shuai
 * @create 2023/8/9 9:05
 * @Instruction
 */
@Builder
public class OrderStatisticsVO implements Serializable {


    private Integer confirmed;//待派送数量
    private Integer deliveryInProgress;//派送中数量
    private Integer toBeConfirmed;//待接单数量

    public OrderStatisticsVO(){}

    public OrderStatisticsVO(Integer confirmed, Integer deliveryInProgress, Integer toBeConfirmed) {
        this.confirmed = confirmed;
        this.deliveryInProgress = deliveryInProgress;
        this.toBeConfirmed = toBeConfirmed;
    }

    public Integer getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
    }

    public Integer getDeliveryInProgress() {
        return deliveryInProgress;
    }

    public void setDeliveryInProgress(Integer deliveryInProgress) {
        this.deliveryInProgress = deliveryInProgress;
    }

    public Integer getToBeConfirmed() {
        return toBeConfirmed;
    }

    public void setToBeConfirmed(Integer toBeConfirmed) {
        this.toBeConfirmed = toBeConfirmed;
    }

    @Override
    public String toString() {
        return "OrderStatisticsVO{" +
                "confirmed=" + confirmed +
                ", deliveryInProgress=" + deliveryInProgress +
                ", toBeConfirmed=" + toBeConfirmed +
                '}';
    }
}
