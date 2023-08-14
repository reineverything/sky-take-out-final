package com.skycommon.exception;

/**
 * @ClassName BaseException
 * @Author shuai
 * @create 2023/7/28 21:34
 * @Instruction 关键异常，是数据异常和业务异常的父类
 */
public class BaseException extends RuntimeException{

    public BaseException(){}
    public BaseException(String msg){
        super(msg);
    }

}
