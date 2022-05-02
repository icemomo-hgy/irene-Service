package com.irene.Excetion;

import com.irene.common.Code;
import com.irene.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.ResultSet;
/**
 * @author 黄冠瑛
 * @date 2022/5/3
 * 异常处理类
 */
@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler(SystemException.class)
    public Result doSystemException(SystemException e){
        return Result.error(Code.SYSTEM_UNKNOW_ERR,e.getMessage());
    }
    @ExceptionHandler(BusinessException.class)
    public Result doBusinessException(BusinessException e){
        return Result.error(Code.BUSINESS_ERR,e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result doOtherException(Exception ex){
        return Result.error(Code.SYSTEM_UNKNOW_ERR, ex.getMessage());
    }
}
