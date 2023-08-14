package com.skypojo.vo;

import java.io.Serializable;

/**
 * @ClassName SetmealOverViewVO
 * @Author shuai
 * @create 2023/8/14 15:36
 * @Instruction
 */
public class SetmealOverViewVO implements Serializable {

    private Integer discontinued;//停售
    private Integer sold;//在售
    public SetmealOverViewVO(){}

    public SetmealOverViewVO(Integer discontinued, Integer sold) {
        this.discontinued = discontinued;
        this.sold = sold;
    }

    public Integer getDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(Integer discontinued) {
        this.discontinued = discontinued;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSolid(Integer sold) {
        this.sold = sold;
    }
}
