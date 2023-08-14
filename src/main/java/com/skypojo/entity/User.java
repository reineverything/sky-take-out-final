package com.skypojo.entity;

import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @ClassName User
 * @Author shuai
 * @create 2023/8/3 9:35
 * @Instruction 用户实体
 */

@Builder
public class User implements Serializable {

    private Long id;
    private String openid;
    private String name;
    private String phone;
    private String sex;
    private String idNumber;
    private String avatar;
    private LocalDateTime createTime;

    public User(Long id, String openid, String name, String phone, String sex, String idNumber, String avatar, LocalDateTime createTime) {
        this.id = id;
        this.openid = openid;
        this.name = name;
        this.phone = phone;
        this.sex = sex;
        this.idNumber = idNumber;
        this.avatar = avatar;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
