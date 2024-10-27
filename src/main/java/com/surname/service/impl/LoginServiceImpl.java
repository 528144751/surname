package com.surname.service.impl;

import com.surname.dto.User;
import com.surname.dto.UserLogs;
import com.surname.mapper.UserLogsMapper;
import com.surname.mapper.UserMapper;
import com.surname.service.LoginService;
import com.surname.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Date;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserLogsMapper userLogsMapper;

    @Override
    public User findUserName(String userName) {
        return userMapper.findUserName(userName);
    }

    @Override
    public void register(User user) {
        //通过md5进行加密
        String passwordMd5 = SecurityUtils.inputPassToFormPass(user.getUserPassword());
        user.setUserPassword(passwordMd5);
        userMapper.register(user);
    }

    @Override
    public void insertUserLogs(User user, String methodName, Object result) throws Exception{
        //写入日志表
        UserLogs userLogs = new UserLogs();
        userLogs.setUserName(user.getUserName());
        userLogs.setControls("login".equals(methodName)?"登录":"退出");
        userLogs.setRemark(result.toString());
        userLogs.setCreateTime(new Date());
        userLogsMapper.insertUserLogs(userLogs);

        //如果是登录，增加登录次数，同时将登录时间写入UserLoginTime.txt文件中
        if ("login".equals(methodName)){
            User userEntity = userMapper.findUserName(user.getUserName());
            userEntity.setLoginNum(userEntity.getLoginNum()+1);
            userMapper.updateUser(userEntity);

            String content = "登录用户: "+ user.getUserName() + " 最新登录时间为： " + new Date();
            Path path = Paths.get("src/main/java/com/surname/UserLoginTime.txt");
            Files.write(path, content.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }
}
