package com.skypojo.dto;

import java.io.Serializable;

/**
 * @ClassName CategoryDTO
 * @Author shuai
 * @create 2023/7/31 10:58
 * @Instruction 新增菜品类
 */
public class CategoryDTO implements Serializable {
    private Long id;
    private Integer type;
    private String name;
    private Integer sort;

    public CategoryDTO(Long id,Integer type, String name, Integer sort) {
        this.id= id;
        this.type = type;
        this.name = name;
        this.sort = sort;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
