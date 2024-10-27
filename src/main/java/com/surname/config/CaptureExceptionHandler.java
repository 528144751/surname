package com.surname.config;

import com.surname.responseResult.ResponseResult;
import com.surname.enumEntity.ResultStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CaptureExceptionHandler {

    /**
     * 全局异常捕捉处理
     */
    @ExceptionHandler(value = Exception.class)
    public ResponseResult errorHandler(Exception ex) {
        log.error("-------------> 全局异常 :  ", ex);
        return ResponseResult.error(ResultStatus.INTERNAL_SERVER_ERROR.getCode(), ResultStatus.INTERNAL_SERVER_ERROR.getMessage());
    }

}
