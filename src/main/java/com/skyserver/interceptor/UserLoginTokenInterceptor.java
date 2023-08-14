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
 * @ClassName UserLoginTokenInteceptor
 * @Author shuai
 * @create 2023/8/3 14:07
 * @Instruction
 */

@Slf4j
@Component
public class UserLoginTokenInterceptor implements HandlerInterceptor {

    @Autowired
    private Environment environment;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("拦截到了请求：{}",request.getRequestURL().toString());

        //获取请求头中令牌，authentication
        String authentication = request.getHeader(environment.getProperty("sky.jwt.user-token-name"));

        //如果令牌为空，返回401
        if(!StringUtils.hasLength(authentication)){
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }

        //令牌不空，校验令牌是否合法
        try {
            Claims claims = JwtUtil.parseJWT(environment.getProperty("sky.jwt.user-secret-key"), authentication);
            log.info("claims:{}",claims);

            //同样需要将当前登录的用户id放入threadLocal中
            ThreadLocalUtil.set(claims.get(JwtClaimConstant.USER_ID));
        }catch (Exception ex){
            log.info("令牌非法，响应401");
            response.setStatus(HttpStatus.UNAUTHORIZED.value());//常量接口401
            return false;
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ThreadLocalUtil.remove();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
