package com.skyserver.service;

import com.skypojo.dto.UserLoginDTO;
import com.skypojo.entity.User;

/**
 * @ClassName UserService
 * @Author shuai
 * @create 2023/8/3 9:32
 * @Instruction
 */
public interface UserService {

    User login(UserLoginDTO userLoginDTO);
}
