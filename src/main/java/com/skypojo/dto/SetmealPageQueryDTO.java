package com.skypojo.dto;

import java.io.Serializable;

/**
 * @ClassName SetmealPageQueryDTO
 * @Author shuai
 * @create 2023/8/3 16:26
 * @Instruction
 */
public class SetmealPageQueryDTO implements Serializable {

    private String name;
    private Long categoryId;//分类号
    private Integer status;//状态
    private Integer page;
    private Integer pageSize;

    public SetmealPageQueryDTO(){}

    public SetmealPageQueryDTO(String name, Long categoryId, Integer status, Integer page, Integer pageSize) {
        this.name = name;
        this.categoryId = categoryId;
        this.status = status;
        this.page = page;
        this.pageSize = pageSize;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "SetmealPageQueryDTO{" +
                "name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", status=" + status +
                ", page=" + page +
                ", pageSize=" + pageSize +
                '}';
    }
}
