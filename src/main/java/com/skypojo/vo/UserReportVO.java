package com.skypojo.vo;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName UserReportVO
 * @Author shuai
 * @create 2023/8/13 16:25
 * @Instruction 用户统计
 */
public class UserReportVO implements Serializable {

    private String dateList;
    private String totalUserList;//全部用户
    private String newUserList;//当天新增用户
    public UserReportVO(){}

    public UserReportVO(String dateList, String totalUserList, String newUserList) {
        this.dateList = dateList;
        this.totalUserList = totalUserList;
        this.newUserList = newUserList;
    }

    public String getDateList() {
        return dateList;
    }

    public void setDateList(String dateList) {
        this.dateList = dateList;
    }

    public String getTotalUserList() {
        return totalUserList;
    }

    public void setTotalUserList(String totalUserList) {
        this.totalUserList = totalUserList;
    }

    public String getNewUserList() {
        return newUserList;
    }

    public void setNewUserList(String newUserList) {
        this.newUserList = newUserList;
    }

    @Override
    public String toString() {
        return "UserReportVO{" +
                "dateList=" + dateList +
                ", totalUserList=" + totalUserList +
                ", newUserList=" + newUserList +
                '}';
    }
}
