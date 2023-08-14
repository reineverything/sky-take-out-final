package com.skypojo.dto;

import java.io.Serializable;

/**
 * @ClassName CategoryPageQueryDTO
 * @Author shuai
 * @create 2023/7/31 9:44
 * @Instruction 封装前端的分页查询数据
 */
public class CategoryPageQueryDTO implements Serializable {

    private String name;
    private Integer type;
    private Integer page;
    private Integer pageSize;

    public CategoryPageQueryDTO(String name, Integer type, Integer page, Integer pageSize) {
        this.name = name;
        this.type = type;
        this.page = page;
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
