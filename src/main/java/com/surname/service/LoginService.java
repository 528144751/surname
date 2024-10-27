package com.surname.service;

import com.surname.dto.User;
import org.springframework.beans.factory.annotation.Autowired;

public interface LoginService {

    /**
     * 查询数据库中用户名是否存在
     * @param userName  用户名
     * @return
     */
    User findUserName(String userName);

    /**
     * 保存用户信息
     * @param user
     */
    void register(User user);

    /**
     * 记录登录登出日志表
     * @param user
     * @param methodName
     */
    void insertUserLogs(User user, String methodName, Object result) throws Exception;
}
