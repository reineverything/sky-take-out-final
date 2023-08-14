package com.skyserver.interceptor;

import com.skycommon.constant.JwtClaimConstant;
import com.skycommon.utils.JwtUtil;
import com.skycommon.utils.ThreadLocalUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName AdminLoginTokenInteceptor
 * @Author shuai
 * @create 2023/7/29 12:31
 * @Instruction 管理端的拦截器，当用户每次访问接口时，都要在请求头中返回token，交给拦截器校验
 */

/**
 * 1。拦截器需要实现HandlerInterceptor接口
 * 2。并且Ctrl+O重写三个方法
 * 3。拦截逻辑写在PreHandle中
 * 4。获取请求头中的token，校验
 * 5。注意拦截器可以配置拦截路径，登录请求不应该被拦截！！！
 */

@Slf4j
@Component
public class AdminLoginTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private Environment environment;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("拦截到了请求：{}",request.getRequestURL().toString());//获取请求路径方法

        //获取请求头中令牌
        String token = request.getHeader(environment.getProperty("sky.jwt.admin-token-name"));
        log.info("token：{}",token);

        //判断令牌是否存在，不存在不放行，返回401
        //401-用户没有权限，需要重新登录
        if(!StringUtils.hasLength(token)){
            log.info("令牌为空，响应401");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());//常量接口401
            return false;
        }

        //校验令牌，失败则401
        try{
            Claims claims = JwtUtil.parseJWT(environment.getProperty("sky.jwt.admin-secret-key"),token);
            log.info("claims：{}",claims);
            /*
            令牌校验成功后，应该将存放的id放入ThreadLocal线程中，便于使用。
            Claim继承map接口，取用方便
             */
            ThreadLocalUtil.set(claims.get(JwtClaimConstant.EMP_ID));

        }catch (Exception exception){
            log.info("令牌非法，响应401");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());//常量接口401
            return false;
        }

        //校验通过，放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        //处理ThreadLocal，防止内存泄露
        ThreadLocalUtil.remove();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
