package com.irene.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result<T> {
    private Integer code; //状态码
    private String msg; //错误信息
    private T data;
    private String status;
    private Map map = new HashMap(); //动态数据

    public static <T> Result<T> success(Integer code,T data,String msg){
       Result<T> result = new Result<>();
       result.code = code;
       result.data = data;
       result.status = "OK";
       result.msg = msg;
       return result;
    }
    public static <T> Result<T> success(Integer code,String msg){
        Result<T> result = new Result<>();
        result.code = code;
      result.msg = msg;
        result.status = "OK";
        return result;
    }

    public static  <T> Result<T> error(Integer code,String msg){
        Result<T> result = new Result<>();
        result.status = "Error";
        result.code = code;
        result.msg = msg;
        return  result;

    }
    public  Result<T> add(String key, T value){
        this.map.put(key,value);
        return this;
    }
    public Result(){}

    public Result(Integer code,T data,String status){
        this.code=code;
        this.data=data;
        this.status = status;
    }


}
