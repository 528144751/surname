package com.surname.mapper;

import com.surname.dto.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
    User findUserName(String userName);

    void register(User user);

    void updateUser(User userEntity);
}
