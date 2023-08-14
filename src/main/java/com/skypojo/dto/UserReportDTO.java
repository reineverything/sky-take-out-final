package com.skypojo.dto;

import java.io.Serializable;

/**
 * @ClassName UserReportDTO
 * @Author shuai
 * @create 2023/8/13 16:38
 * @Instruction
 */
public class UserReportDTO implements Serializable {

    private String createDate;
    private Integer userCount;
    public UserReportDTO(){}

    public UserReportDTO(String createDate, Integer userCount) {
        this.createDate = createDate;
        this.userCount = userCount;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public Integer getUserCount() {
        return userCount;
    }

    public void setUserCount(Integer userCount) {
        this.userCount = userCount;
    }
}
