package com.surname.mapper;

import com.surname.dto.UserLogs;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserLogsMapper {
    void insertUserLogs(UserLogs userLogs);
}
