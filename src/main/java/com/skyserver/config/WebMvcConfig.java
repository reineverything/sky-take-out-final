package com.skyserver.config;

import com.skyserver.interceptor.AdminLoginTokenInterceptor;
import com.skyserver.interceptor.UserLoginTokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName WebMvcConfig
 * @Author shuai
 * @create 2023/7/29 13:00
 * @Instruction 拦截器配置
 */

/**
 * 1。拦截器实现WebMvcConfiguration接口
 * 2。添加@Configuration注解
 * 3。重写addInterceptor方法
 * 4。注入要配置的拦截器
 * 拦截路径：/**拦截所有，/*只会拦截一层比如/abc
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private AdminLoginTokenInterceptor adminLoginTokenInterceptor;

    @Autowired
    private UserLoginTokenInterceptor userLoginTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //以后需要将路径重新拦截
        //拦截除了登录外的所有请求
        registry.addInterceptor(adminLoginTokenInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/employee/login");

        registry.addInterceptor(userLoginTokenInterceptor)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/user/login","/user/shop/status");

    }

    /**
     * 配置跨域
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //是否发送Cookie
                .allowCredentials(true)
                //放行哪些原始域
                .allowedOriginPatterns("*")
                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"})
                .allowedHeaders("*")
                .exposedHeaders("*");
    }
}
