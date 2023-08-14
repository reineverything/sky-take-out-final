package com.skypojo.vo;

import java.io.Serializable;

/**
 * @ClassName DishOverViewVO
 * @Author shuai
 * @create 2023/8/14 15:51
 * @Instruction
 */
public class DishOverViewVO implements Serializable {
    private Integer discontinued;
    private Integer sold;
    public DishOverViewVO(){}

    public DishOverViewVO(Integer discontinued, Integer sold) {
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

    public void setSold(Integer sold) {
        this.sold = sold;
    }
}
