package com.skypojo.dto;

import java.io.Serializable;

/**
 * @ClassName EmployeeDTO
 * @Author shuai
 * @create 2023/7/28 20:59
 * @Instruction 架构之间数据传输
 */
public class EmployeeLoginDTO implements Serializable {

    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
