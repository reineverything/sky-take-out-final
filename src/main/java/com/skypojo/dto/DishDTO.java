package com.skypojo.dto;

import com.skypojo.entity.DishFlavor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName DishDTO
 * @Author shuai
 * @create 2023/8/1 13:39
 * @Instruction 新增菜品接收类
 */
public class DishDTO implements Serializable {

    private Long id;
    private String name;
    private Long categoryId;
    private BigDecimal price;//java记录浮点型
    private String image;
    private String description;
    private Integer status;
    private List<DishFlavor> flavors=new ArrayList<>();//口味

    public DishDTO(){}

    public DishDTO(Long id,String name, Long categoryId, BigDecimal price, String image, String description, Integer status, List<DishFlavor> flavors) {
        this.id = id;
        this.name=name;
        this.categoryId = categoryId;
        this.price = price;
        this.image = image;
        this.description = description;
        this.status = status;
        this.flavors = flavors;
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

    public List<DishFlavor> getFlavors() {
        return flavors;
    }

    public void setFlavors(List<DishFlavor> flavors) {
        this.flavors = flavors;
    }

    @Override
    public String toString() {
        return "DishDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", flavors=" + flavors +
                '}';
    }

}
