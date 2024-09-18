package org.example.todolistserver.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.todolistserver.model.UserDO;

@Mapper
public interface UserMapper {
    UserDO select(String name);
    int insert(UserDO user);
}
