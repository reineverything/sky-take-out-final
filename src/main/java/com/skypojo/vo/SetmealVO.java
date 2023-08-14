package com.skypojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.skypojo.entity.SetmealDish;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SetmealVO
 * @Author shuai
 * @create 2023/8/3 16:39
 * @Instruction
 */
public class SetmealVO implements Serializable {

    private Long id;
    private String name;
    private Long categoryId;
    private BigDecimal price;
    private String image;
    private String description;
    private Integer status;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    private String categoryName;

    private List<SetmealDish> setmealDishes=new ArrayList<>();
    public SetmealVO(){}

    public SetmealVO(Long id, String name, Long categoryId, BigDecimal price, String image, String description, Integer status, LocalDateTime updateTime, String categoryName, List<SetmealDish> setmealDishes) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
        this.image = image;
        this.description = description;
        this.status = status;
        this.updateTime = updateTime;
        this.categoryName = categoryName;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<SetmealDish> getSetmealDishes() {
        return setmealDishes;
    }

    public void setSetmealDishes(List<SetmealDish> setmealDishes) {
        this.setmealDishes = setmealDishes;
    }

    @Override
    public String toString() {
        return "SetmealVO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", updateTime=" + updateTime +
                ", categoryName='" + categoryName + '\'' +
                ", setmealDishes=" + setmealDishes +
                '}';
    }
}
