package com.skypojo.dto;

import java.io.Serializable;

/**
 * @ClassName OrdersConfirmDTO
 * @Author shuai
 * @create 2023/8/9 19:59
 * @Instruction 商家接单类
 */
public class OrdersConfirmDTO implements Serializable {
    private Long id;

    public OrdersConfirmDTO(){}

    public OrdersConfirmDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "OrdersConfirmDTO{" +
                "id=" + id +
                '}';
    }
}
