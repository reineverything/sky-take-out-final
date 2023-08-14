package com.skyserver.config;

import com.skycommon.properties.AliOssProperties;
import com.skycommon.utils.AliOssUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName AliOssConfig
 * @Author shuai
 * @create 2023/8/1 11:43
 * @Instruction 阿里云配置类
 */

@Configuration
public class AliOssConfig {

    @Bean
    @ConditionalOnMissingBean //只有在用到时才会创建bean
    public AliOssUtil aliOssUtil(AliOssProperties aliOssProperties){//注入第三方bean，在方法形参中声明！！！
        return new AliOssUtil(aliOssProperties.getEndpoint(), aliOssProperties.getAccessKeyId(), aliOssProperties.getAccessKeySecret(), aliOssProperties.getBucketName());

    }
}
