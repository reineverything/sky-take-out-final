package com.skypojo.dto;

import com.skypojo.entity.SetmealDish;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SetmealDTO
 * @Author shuai
 * @create 2023/8/4 13:57
 * @Instruction
 */
public class SetmealDTO implements Serializable {

    private Long id;
    private String name;
    private BigDecimal price;
    private String image;
    private String description;
    private Long categoryId;

    private List<SetmealDish> setmealDishes=new ArrayList<>();

    public SetmealDTO(){}

    public SetmealDTO(Long id, String name, BigDecimal price, String image, String description, Long categoryId, List<SetmealDish> setmealDishes) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
        this.description = description;
        this.categoryId = categoryId;
        this.setmealDishes = setmealDishes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<SetmealDish> getSetmealDishes() {
        return setmealDishes;
    }

    public void setSetmealDishes(List<SetmealDish> setmealDishes) {
        this.setmealDishes = setmealDishes;
    }

    @Override
    public String toString() {
        return "SetmealDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                ", setmealDishes=" + setmealDishes +
                '}';
    }
}
