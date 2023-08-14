package com.skypojo.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @ClassName SetmealDish
 * @Author shuai
 * @create 2023/8/4 14:06
 * @Instruction 套餐菜品关联表
 */
public class SetmealDish implements Serializable {

    private Long id;
    private Long setmealId;
    private Long dishId;
    private String name;
    private BigDecimal price;
    private Integer copies;

    public SetmealDish(){}

    public SetmealDish(Long id, Long setmealId, Long dishId, String name, BigDecimal price, Integer copies) {
        this.id = id;
        this.setmealId = setmealId;
        this.dishId = dishId;
        this.name = name;
        this.price = price;
        this.copies = copies;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSetmealId() {
        return setmealId;
    }

    public void setSetmealId(Long setmealId) {
        this.setmealId = setmealId;
    }

    public Long getDishId() {
        return dishId;
    }

    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }
}
