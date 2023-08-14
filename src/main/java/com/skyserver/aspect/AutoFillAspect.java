package com.skyserver.aspect;

import com.skycommon.constant.AutoFillConstant;
import com.skycommon.enumeration.OperationType;
import com.skycommon.utils.ThreadLocalUtil;
import com.skyserver.annotation.AutoFill;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * @ClassName AutoFillAspect
 * @Author shuai
 * @create 2023/7/31 16:30
 * @Instruction aop利用注解和反射为公共字段自动填充
 */

/**
 * 切面类制作流程：
 * @Aspect:切面类
 * @Component:交给spring
 * aop5种通知类型：before,after returning,after throwing,after,around！！！由于需要在insert或者update执行之前为其赋值，可以使用before或者around
 */

@Slf4j
@Aspect
@Component
public class AutoFillAspect {

    /**
     * 使用before实现，原始方法还没有执行，没有返回值
     * execution控制方法执行的作用域，需要好好学习！！！
     */
    @Before("execution(* com.skyserver.mapper.*.*(..)) && @annotation(com.skyserver.annotation.AutoFill)") //before中写切入点表达式！！！表示在被该注解标识的方法执行前会执行before方法
    public void autoFillProperty(JoinPoint joinPoint) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {//除了环绕通知proceedingJoinPoint
        //1。获取方法运行时传入的参数
        Object[] args = joinPoint.getArgs();

        //防止乱标识，需要判断参数个数
        if(ObjectUtils.isEmpty(args)){
            return;
        }

        //拿到数组中第一个参数，即
        Object obj=args[0];
        log.info("赋值前：{}",obj);

        //通过反射获取对应的方法，需要方法名和对应参数
        Method setCreateTime = obj.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
        Method setUpdateTime = obj.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
        Method setCreateUser = obj.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
        Method setUpdateUser = obj.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);

        //需要拿到注解对应的value，判断调用哪些方法
        MethodSignature methodSignature= (MethodSignature) joinPoint.getSignature();
        AutoFill autoFill = methodSignature.getMethod().getAnnotation(AutoFill.class);//获取到AutoFill注解
        OperationType operationType = autoFill.value();//获取注解的属性

        //判断属性
        if(operationType.equals(OperationType.INSERT)){
            setCreateTime.invoke(obj,LocalDateTime.now());
            setCreateUser.invoke(obj, (long)(int)ThreadLocalUtil.get());

        }
        setUpdateTime.invoke(obj,LocalDateTime.now());
        setUpdateUser.invoke(obj, (long)(int)ThreadLocalUtil.get());

        log.info("赋值后：{}",obj);
    }
}
