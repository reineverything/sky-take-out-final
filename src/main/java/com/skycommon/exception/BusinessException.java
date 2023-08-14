package com.skycommon.exception;

/**
 * @ClassName BusinessException
 * @Author shuai
 * @create 2023/7/28 21:37
 * @Instruction 业务异常
 */
public class BusinessException extends BaseException{
    public BusinessException(){}

    public BusinessException(String msg){
        super(msg);
    }
}
