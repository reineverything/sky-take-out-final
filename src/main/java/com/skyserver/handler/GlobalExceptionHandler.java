package com.skyserver.handler;

import com.skycommon.constant.MessageConstant;
import com.skycommon.exception.BaseException;
import com.skycommon.result.Result;
import com.skycommon.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName GlobalExceptionHandler
 * @Author shuai
 * @create 2023/7/28 21:17
 * @Instruction 全局异常处理，在controller以上出现错误时，通过异常层层抛出，最终交给全局异常
 */

@Slf4j
@RestControllerAdvice //将该类标注为全局异常处理器，注解重要！！！！
public class GlobalExceptionHandler {

    //处理业务异常
    @ExceptionHandler(BaseException.class) //@ExceptionHandler 如果不指定参数，默认参数是形参中的异常类型
    public Result baseExceptionHandler(BaseException ex){
        ex.printStackTrace();
        log.info("发生异常：{}",ex.getMessage());
        return Result.error(ex.getMessage());
    }


    /**
     * 解决存在相同账号名或者手机号或者身份证号的问题，采用全局异常捕获解决
     * 数据库插入相同数据都会抛出DuplicateKeyException！！！，在ex.cause.detailMessage中可以清楚看到冲突的数据
     * @param ex
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class) //@ExceptionHandler 如果不指定参数，默认参数是形参中的异常类型
    public Result baseExceptionHandler(DuplicateKeyException ex){
        ex.printStackTrace();
        log.info("发生异常：{}",ex.getMessage());

        String errorMsg = null;
        String message=ex.getCause().getMessage();
        if(StringUtils.hasLength(message)){
            String[] msg=message.split(" ");
            errorMsg=msg[2]+"已存在";//取出重复的数据
        }
        return Result.error(errorMsg);
    }


    @ExceptionHandler(Exception.class) //除了上面方法的异常，其他异常类型被其捕获
    public Result baseExceptionHandler(Exception ex){
        ex.printStackTrace();
        log.info("发生异常：{}",ex.getMessage());
        return Result.error(MessageConstant.UNKNOWN_ERROR);
    }

}
