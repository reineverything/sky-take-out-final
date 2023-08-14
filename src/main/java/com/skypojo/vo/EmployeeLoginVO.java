package com.skypojo.vo;

import lombok.Builder;

import java.io.Serializable;

/**
 * @ClassName EmployeeLoginVO
 * @Author shuai
 * @create 2023/7/28 20:40
 * @Instruction 返回给前端数据
 */

@Builder //@Builder提供了、创建对象的静态方法，总之是对构造函数和getset方法的更便捷方式，依赖属于lombok
public class EmployeeLoginVO implements Serializable {

    private Long id;
    private String username;
    private String name;
    private String token;

    public EmployeeLoginVO(Long id, String username, String name, String token) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
