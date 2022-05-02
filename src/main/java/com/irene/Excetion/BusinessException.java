package com.irene.Excetion;

import lombok.Data;

@Data

/**
 * @author 黄冠瑛
 * @date 2022/5/3
 * 自定义业务级异常
 */

public class BusinessException extends RuntimeException{
    private Integer code;

   public BusinessException(Integer code,String message){
       super(message);
       this.code = code;
   }
    public BusinessException(Integer code,String message,Throwable cause){
        super(message,cause);
        this.code = code;
    }

}
