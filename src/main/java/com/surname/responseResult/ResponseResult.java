package com.surname.responseResult;

import com.surname.enumEntity.ResultStatus;
import lombok.Data;

import java.io.Serializable;

/**
 * 统一返回实体类
 */
@Data
public class ResponseResult implements Serializable {

    private static final long serialVersionUID = -8373949364192372417L;

    //返回码
    private Integer code;
    //返回信息描述
    private String message;
    //返回信息
    private Object data;

    public ResponseResult(){

    }

    public ResponseResult(Integer code, String message, Object data){
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 正确返回
     * @return
     */
    public static  ResponseResult success(Object data){
        return new ResponseResult(ResultStatus.SUCCESS.getCode(), ResultStatus.SUCCESS.getMessage(), data);
    }

    /**
     * 错误返回
     * @return
     */
    public static  ResponseResult error(Integer code, String message){
        return new ResponseResult(code, message, null);
    }

}
