package com.skyserver.controller.user;

import com.skycommon.constant.JwtClaimConstant;
import com.skycommon.result.Result;
import com.skycommon.utils.JwtUtil;
import com.skypojo.dto.UserLoginDTO;
import com.skypojo.entity.User;
import com.skypojo.vo.UserLoginVO;
import com.skyserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @ClassName UserController
 * @Author shuai
 * @create 2023/8/3 9:25
 * @Instruction 用户接口
 */

@Slf4j
@RestController
@RequestMapping("/user/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private Environment environment;

    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userLoginDTO){
        log.info("用户登录：{}",userLoginDTO);

        //调用service
        User user=userService.login(userLoginDTO);

        //生成令牌
        HashMap<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimConstant.USER_ID,user.getId());//保存想在token中存放的信息
        String token = JwtUtil.createJWT(environment.getProperty("sky.jwt.user-secret-key"), Long.parseLong(environment.getProperty("sky.jwt.user-ttl")), claims);

        //返回数据
        UserLoginVO userLoginVO = UserLoginVO.builder()
                .id(user.getId())
                .openId(user.getOpenid())
                .token(token)
                .build();
        return Result.success(userLoginVO);
    }
}
