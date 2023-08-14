package com.skypojo.dto;

import java.io.Serializable;

/**
 * @ClassName EmployeePageQueryDTO
 * @Author shuai
 * @create 2023/7/30 16:52
 * @Instruction 员工分页查询类，用于整合前端的查询输入
 */
public class EmployeePageQueryDTO implements Serializable {

    private String name;//员工姓名
    private Integer page;
    private Integer pageSize;

    public EmployeePageQueryDTO(String name, Integer page, Integer pageSize) {
        this.name = name;
        this.page = page;
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
