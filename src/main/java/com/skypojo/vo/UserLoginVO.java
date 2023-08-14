package com.skypojo.vo;

import lombok.Builder;

import java.io.Serializable;

/**
 * @ClassName UserLoginVO
 * @Author shuai
 * @create 2023/8/3 9:26
 * @Instruction 用户登录返回给前端数据
 */

@Builder
public class UserLoginVO implements Serializable {

    private Long id;
    private String openId;
    private String token;

    public UserLoginVO(){}

    public UserLoginVO(Long id, String openId, String token) {
        this.id = id;
        this.openId = openId;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
