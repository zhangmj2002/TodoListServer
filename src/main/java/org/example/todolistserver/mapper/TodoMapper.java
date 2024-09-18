package org.example.todolistserver.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.todolistserver.model.TodoDO;

import java.util.List;

@Mapper
public interface TodoMapper {
    List<TodoDO> select(TodoDO todoDO);
    List<TodoDO> selectById(int id);
    int insert(TodoDO todoDO);
    int update(TodoDO todoDO);
    int deleteById(int id);
}
