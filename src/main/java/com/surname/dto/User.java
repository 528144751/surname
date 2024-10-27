package com.surname.dto;

import lombok.Data;

/**
 * 用户实体类
 */
@Data
public class User {

    //自增主键
    private Integer id;

    //用户名
    private String userName;

    //密码
    private String userPassword;

    //手机号
    private String phoneNumber;

    //用户邮箱
    private String userEmail;

    //登录次数
    private Integer loginNum;

}
