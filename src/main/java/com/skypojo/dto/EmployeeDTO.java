package com.skypojo.dto;


import java.io.Serializable;

/**
 * @ClassName EmployeeDTO
 * @Author shuai
 * @create 2023/7/29 16:08
 * @Instruction 添加员工类
 */
public class EmployeeDTO implements Serializable {

    private String username;//账号名
    private String name;
    private String phone;
    private String sex;
    private String idNumber;

    public EmployeeDTO(){}

    public EmployeeDTO(String username, String name, String phone, String sex, String idNumber) {
        this.username = username;
        this.name = name;
        this.phone = phone;
        this.sex = sex;
        this.idNumber = idNumber;
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
}
