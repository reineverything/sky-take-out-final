package com.skypojo.vo;

import java.io.Serializable;

/**
 * @ClassName SalesTop10ReportVO
 * @Author shuai
 * @create 2023/8/14 10:14
 * @Instruction
 */
public class SalesTop10ReportVO implements Serializable {

    private String nameList;
    private String numberList;
    public SalesTop10ReportVO(){}

    public SalesTop10ReportVO(String nameList, String numberList) {
        this.nameList = nameList;
        this.numberList = numberList;
    }

    public String getNameList() {
        return nameList;
    }

    public void setNameList(String nameList) {
        this.nameList = nameList;
    }

    public String getNumberList() {
        return numberList;
    }

    public void setNumberList(String numberList) {
        this.numberList = numberList;
    }

    @Override
    public String toString() {
        return "SalesTop10ReportVO{" +
                "nameList='" + nameList + '\'' +
                ", numberList='" + numberList + '\'' +
                '}';
    }
}
