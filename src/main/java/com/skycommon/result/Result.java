package com.skycommon.result;

import java.io.Serializable;

/**
 * @ClassName Result
 * @Author shuai
 * @create 2023/7/28 20:45
 * @Instruction 统一返回结果
 */

/**
 * 要想客户端能够解析服务器的内容，get和set方法是必要的！！！
 * @param <T>
 */
public class Result<T>  implements Serializable {

    private Integer code;//返回码：1表示ok，其他值表示异常
    private String msg;
    private T data;

    //无返回值成功值
    public static Result success(){
        Result result = new Result<>();
        result.code=1;
        return result;
    }

    //成功返回并且携带数据
    public static <T> Result<T> success(T data){
        Result<T> result= new Result<>();
        result.code=1;
        result.data=data;
        return result;
    }

    //返回错误信息
    public static Result error(String msg){
        Result result = new Result<>();
        result.code=0;
        result.msg=msg;
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
