package com.skyserver.service.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skycommon.constant.MessageConstant;
import com.skycommon.exception.BusinessException;
import com.skycommon.properties.WeChatProperties;
import com.skycommon.utils.HttpClientUtil;
import com.skypojo.dto.UserLoginDTO;
import com.skypojo.entity.User;
import com.skyserver.mapper.UserMapper;
import com.skyserver.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Author shuai
 * @create 2023/8/3 9:33
 * @Instruction 用户业务层
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private static final String WEIXIN_LOGIN_URL="https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private WeChatProperties weChatProperties;

    @Override
    public User login(UserLoginDTO userLoginDTO) {//传递code，这是执行微信官方接口，wx.login()自动生成的

        // 1调用微信接口，实现登录httpClient
        //小程序请求样例：
        // https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code

        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("appid", weChatProperties.getAppid());
        paramMap.put("secret", weChatProperties.getSecret());
        paramMap.put("js_code",userLoginDTO.getCode());
        paramMap.put("grant_type","authorization_code");
        String result=HttpClientUtil.doGet(WEIXIN_LOGIN_URL,paramMap);
        log.info("微信登录完成，结果：{}",result);

        if(!StringUtils.hasLength(result)){
            throw new BusinessException(MessageConstant.LOGIN_FAILED);
        }

        //result String类型，json格式，需要解析出openId
        JSONObject jsonObject = JSON.parseObject(result);
        String openid = jsonObject.getString("openid");//用户唯一标识

        //2 如果第一次访问小程序，完成自动注册(insert)
        User user=userMapper.selectByOpenid(openid);
        if(user==null){
            user=User.builder().openid(openid).createTime(LocalDateTime.now()).build();
            userMapper.insert(user);//这里要主键返回
        }
        //3.返回
        return user;

    }
}
