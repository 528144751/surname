package com.surname.dto;

import lombok.Data;

import java.util.Date;

/**
 * 日志实体类
 */
@Data
public class UserLogs {

    //自增主键
    private Integer id;

    //用户名
    private String userName;

    //操作
    private String controls;

    //备注
    private String remark;

    //操作时间
    private Date createTime;

}
