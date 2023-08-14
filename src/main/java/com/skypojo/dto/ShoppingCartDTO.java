package com.skypojo.dto;

import com.skypojo.entity.ShoppingCart;

import java.io.Serializable;

/**
 * @ClassName ShoppingCartDTO
 * @Author shuai
 * @create 2023/8/5 20:10
 * @Instruction 新增购物车接受，包括菜品和套餐，所以需要三个参数
 */
public class ShoppingCartDTO implements Serializable {

    private Long dishId;
    private Long setmealId;
    private String dishFlavor;

    public ShoppingCartDTO(){}

    public ShoppingCartDTO(Long dishId, Long setmealId, String dishFlavor) {
        this.dishId = dishId;
        this.setmealId = setmealId;
        this.dishFlavor = dishFlavor;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public Long getSetmealId() {
        return setmealId;
    }

    public void setSetmealId(Long setmealId) {
        this.setmealId = setmealId;
    }

    public String getDishFlavor() {
        return dishFlavor;
    }

    public void setDishFlavor(String dishFlavor) {
        this.dishFlavor = dishFlavor;
    }

    @Override
    public String toString() {
        return "ShoppingCartDTO{" +
                "dishId=" + dishId +
                ", setmealId=" + setmealId +
                ", dishFlavor='" + dishFlavor + '\'' +
                '}';
    }

}
