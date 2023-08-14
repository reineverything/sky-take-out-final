package com.skypojo.dto;

import java.io.Serializable;

/**
 * @ClassName OrdersRejectionDTO
 * @Author shuai
 * @create 2023/8/9 16:31
 * @Instruction 商家拒单理由
 */
public class OrdersCancelDTO implements Serializable {

    private Long id;
    private String cancelReason;
    public OrdersCancelDTO(){}

    public OrdersCancelDTO(Long id, String cancelReason) {
        this.id = id;
        this.cancelReason = cancelReason;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason;
    }

    @Override
    public String toString() {
        return "OrdersCancelDTO{" +
                "id=" + id +
                ", cancelReason='" + cancelReason + '\'' +
                '}';
    }
}
