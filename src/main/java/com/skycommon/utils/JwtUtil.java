package com.skycommon.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 * @ClassName JwtUtil
 * @Author shuai
 * @create 2023/7/29 8:22
 * @Instruction jwt工具类
 */
public class JwtUtil {

    /**
     * @param secretKey 密钥
     * @param ttlMills 有效时间，毫秒
     * @param claims 携带的数据
     * @return token
     */

    //密钥时间的设置会放在yml中！！！

    public static String createJWT(String secretKey, long ttlMills, Map<String,Object> claims){
        //1。加密算法
        SignatureAlgorithm signatureAlgorithm=SignatureAlgorithm.HS256;

        //2。计算失效时间
        long expMills=System.currentTimeMillis()+ttlMills;
        Date exp = new Date(expMills);

        //3。设置jwt的body
        JwtBuilder builder= Jwts.builder()//创建令牌时有创建器
                .setClaims(claims)
                .signWith(signatureAlgorithm,secretKey.getBytes(StandardCharsets.UTF_8))
                .setExpiration(exp);
        return builder.compact();

    }

    /**
     * @param secretKey 密钥
     * @param token 令牌
     * @return Claims是声明信息，当校验密钥不正确时会抛出异常
     */
    public static Claims parseJWT(String secretKey,String token){
        //解析令牌时有解析器
        return Jwts.parser()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))//这里编码很重要，找了很久才发现
                .parseClaimsJws(token)
                .getBody();
    }
}
