package com.skypojo.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName TurnoverReportVO
 * @Author shuai
 * @create 2023/8/10 17:28
 * @Instruction 营业额
 */
public class TurnoverReportVO implements Serializable {

    private String dateList;//时间
    private String turnoverList;//营业额

    public TurnoverReportVO(){

    }

    public TurnoverReportVO(String dateList, String turnoverList) {
        this.dateList = dateList;
        this.turnoverList = turnoverList;
    }

    public String getDateList() {
        return dateList;
    }

    public void setDateList(String dateList) {
        this.dateList = dateList;
    }

    public String getTurnoverList() {
        return turnoverList;
    }

    public void setTurnoverList(String turnoverList) {
        this.turnoverList = turnoverList;
    }

    @Override
    public String toString() {
        return "TurnoverReportVO{" +
                "dateList=" + dateList +
                ", turnoverList=" + turnoverList +
                '}';
    }
}
