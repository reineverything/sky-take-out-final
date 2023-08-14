package com.skypojo.vo;

import java.io.Serializable;

/**
 * @ClassName SetmealDishVO
 * @Author shuai
 * @create 2023/8/5 10:07
 * @Instruction 套餐下的菜品信息
 */
public class SetmealDishVO implements Serializable {

    private String name;
    private String image;
    private String description;
    private Integer copies;
    public SetmealDishVO(){}

    public SetmealDishVO(String name, String image, String description, Integer copies) {
        this.name = name;
        this.image = image;
        this.description = description;
        this.copies = copies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCopies() {
        return copies;
    }

    public void setCopies(Integer copies) {
        this.copies = copies;
    }
}
