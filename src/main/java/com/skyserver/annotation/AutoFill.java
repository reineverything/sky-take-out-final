package com.skyserver.annotation;

import com.skycommon.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName AutoFill
 * @Author shuai
 * @create 2023/7/31 16:19
 * @Instruction 自动填充注解，注意自定义注解的写法！！！
 * 自定义注解一般要写上几个元注解
 */

@Retention(RetentionPolicy.RUNTIME) //在运行时生效
@Target(ElementType.METHOD)  //作用在方法上
public @interface AutoFill {

    //属性用来标识到底是新增还是修改
    OperationType value();
}
