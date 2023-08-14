package com.skycommon.utils;

/**
 * @ClassName ThreadLocalUtil
 * @Author shuai
 * @create 2023/7/30 9:48
 * @Instruction ThreadLocal工具类
 */
public class ThreadLocalUtil {

    public static ThreadLocal<Object> threadLocal=new ThreadLocal<>();

    public static void set(Object o){
        threadLocal.set(o);
    }

    public static Object get(){
        return threadLocal.get();
    }

    public static void remove(){
        threadLocal.remove();
    }
}
