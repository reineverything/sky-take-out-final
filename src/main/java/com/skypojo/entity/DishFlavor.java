package com.skypojo.entity;

import java.io.Serializable;

/**
 * @ClassName DishFlavor
 * @Author shuai
 * @create 2023/8/1 13:37
 * @Instruction 菜品口味类
 */
public class DishFlavor implements Serializable {

    private Long id;
    private Long dishId;
    private String name;//忌口
    private String value;//不要辣，不要香菜

    public DishFlavor(){}

    public DishFlavor(Long id, Long dishId, String name, String value) {
        this.id = id;
        this.dishId = dishId;
        this.name = name;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
