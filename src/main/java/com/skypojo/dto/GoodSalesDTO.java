package com.skypojo.dto;

import java.io.Serializable;

/**
 * @ClassName GoodSalesDTO
 * @Author shuai
 * @create 2023/8/14 10:27
 * @Instruction
 */
public class GoodSalesDTO implements Serializable {

    private String name;
    private Integer number;
    public GoodSalesDTO(){}

    public GoodSalesDTO(String name, Integer number) {
        this.name = name;
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}
