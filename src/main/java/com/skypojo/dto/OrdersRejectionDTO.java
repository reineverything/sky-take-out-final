package com.skypojo.dto;

import java.io.Serializable;

/**
 * @ClassName OrdersRejectionDTO
 * @Author shuai
 * @create 2023/8/9 17:29
 * @Instruction 商家拒单类
 */
public class OrdersRejectionDTO implements Serializable {
    private Long id;
    private String rejectionReason;

    public OrdersRejectionDTO(){}

    public OrdersRejectionDTO(Long id, String rejectionReason) {
        this.id = id;
        this.rejectionReason = rejectionReason;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    @Override
    public String toString() {
        return "OrdersRejectionDTO{" +
                "id=" + id +
                ", rejectionReason='" + rejectionReason + '\'' +
                '}';
    }
}
