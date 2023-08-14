package com.skypojo.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName OrdersPageQueryDTO
 * @Author shuai
 * @create 2023/8/8 10:39
 * @Instruction 订单查询
 */
public class OrdersPageQueryDTO implements Serializable {

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime beginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;
    private String number;
    private Integer page;
    private Integer pageSize;
    private String phone;
    private Integer status;

    public OrdersPageQueryDTO(){}

    public OrdersPageQueryDTO(LocalDateTime beginTime, LocalDateTime endTime, String number, Integer page, Integer pageSize, String phone, Integer status) {
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.number = number;
        this.page = page;
        this.pageSize = pageSize;
        this.phone = phone;
        this.status = status;
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(LocalDateTime beginTime) {
        this.beginTime = beginTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrdersPageQueryDTO{" +
                "beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", number=" + number +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                '}';
    }
}
