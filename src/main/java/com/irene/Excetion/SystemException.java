package com.irene.Excetion;

import lombok.Data;

/**
 * @author 黄冠瑛
 * @date 2022/5/3
 * 自定义系统级异常
 *
 */
@Data
public class SystemException extends RuntimeException{
    private Integer code;

    public SystemException(Integer code,String message){
        super(message);
        this.code  = code;

    }
    public SystemException(Integer code,String message,Throwable cause){
        super(message,cause);
        this.code = code;
   }


}
