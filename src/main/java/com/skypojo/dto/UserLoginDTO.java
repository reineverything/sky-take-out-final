package com.skypojo.dto;


/**
 * @ClassName UserLoginDTO
 * @Author shuai
 * @create 2023/8/3 9:29
 * @Instruction 用户登录请求参数
 */
public class UserLoginDTO {

    private String code;//微信授权码

    public UserLoginDTO(){}

    public UserLoginDTO(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "UserLoginDTO{" +
                "code='" + code + '\'' +
                '}';
    }
}
