package com.skycommon.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @ClassName WeChatProperties
 * @Author shuai
 * @create 2023/8/3 9:54
 * @Instruction 微信属性配置
 */

@Component
@ConfigurationProperties(prefix = "wechat")
public class WeChatProperties {

    private String appid;
    private String secret;

    public WeChatProperties(){}
    public WeChatProperties(String appid, String secret) {
        this.appid = appid;
        this.secret = secret;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
