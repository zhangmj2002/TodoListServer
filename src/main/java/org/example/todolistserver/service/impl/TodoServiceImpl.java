package org.example.todolistserver.service.impl;

import jakarta.annotation.Resource;
import org.example.todolistserver.controller.dto.TodoDTO;
import org.example.todolistserver.enums.PriorityEnum;
import org.example.todolistserver.enums.StatusEnum;
import org.example.todolistserver.general.Result;
import org.example.todolistserver.mapper.TodoMapper;
import org.example.todolistserver.model.TodoDO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


@Service
public class TodoServiceImpl {

    @Resource
    private TodoMapper todoMapper;

    /**
     * Get the todo list based on sortType(dueDate, priority) and showType(ongoing, finished)
     * @param username
     * @param sortType sort based on: dueDate, priority
     * @param showType ongoing, finished
     * @return current user's todo list
     */
    public Result<List<TodoDTO>> getTodoList(String username, int sortType, int showType) {
        try {
            TodoDO todoDO = new TodoDO();
            todoDO.setOwners(username);
            todoDO.setStatus(showType);
            List<TodoDO> todoDOList = todoMapper.select(todoDO);

            // Do the sort
            todoDOList = sortTodoList(todoDOList, sortType);
            List<TodoDTO> todoDTOList = new ArrayList<>();
            todoDOList.forEach(item -> todoDTOList.add(convertTodoDO2DTO(item)));

            return Result.success(todoDTOList);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            return Result.error(e.getMessage());
        }
    }

    /**
     * Set a todo to finished status
     * @param id
     * @return
     */
    public Result<Boolean> finishTodo(int id) {
        try {
            List<TodoDO> todoDOList = todoMapper.selectById(id);
            if (todoDOList != null && !todoDOList.isEmpty()) {
                TodoDO updater = todoDOList.get(0);
                updater.setStatus(StatusEnum.FINISHED.getIndex());
                if (todoMapper.update(updater) == 1) {
                    return Result.success();
                }
                return Result.error();
            }
            return Result.error("Didn't find todo with id " + id);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            return Result.error(e.getMessage());
        }
    }

    /**
     * Delete a todo
     * @param id
     * @return
     */
    public Result<Boolean> deleteTodo(int id) {
        try {
            List<TodoDO> todoDOList = todoMapper.selectById(id);
            if (todoDOList != null && !todoDOList.isEmpty()) {
                if (todoMapper.deleteById(id) == 1) {
                    return Result.success();
                }
                return Result.error();
            }
            return Result.error("Didn't find todo with id " + id);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            return Result.error(e.getMessage());
        }
    }

    /**
     * Update a todo
     * @param todoDTO
     * @return
     */
    public Result<Boolean> insertOrUpdateTodo(TodoDTO todoDTO) {
        try {
            TodoDO todoDO = convertTodoDTO2DO(todoDTO);
            List<TodoDO> todoDOList = todoMapper.selectById(todoDO.getId());
            if (todoDOList != null && !todoDOList.isEmpty()) {
                // Update
                if (todoMapper.update(todoDO) == 1) {
                    return Result.success();
                }
                return Result.error();
            } else {
                // insert
                todoDO.setStatus(1);
                if (todoMapper.insert(todoDO) == 1) {
                    return Result.success();
                }
                return Result.error();
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            return Result.error(e.getMessage());
        }
    }

    private List<TodoDO> sortTodoList(List<TodoDO> todoDOList, int sortType) {
        List<TodoDO> result = null;
        switch (sortType) {
            case 1:
                result = todoDOList.stream().sorted(
                        Comparator.comparingLong(t -> t.getDueDate().getTime())).toList();
                break;
            case 2:
                result = todoDOList.stream().sorted(
                        Comparator.comparingInt(TodoDO::getPriority)).toList();
                break;
            case 3:
                result = todoDOList.stream().sorted(
                        Comparator.comparing(TodoDO::getTitle)).toList();
                break;
            default:
                break;
        }

        return result;
    }

    private TodoDTO convertTodoDO2DTO(TodoDO todoDO) {
        TodoDTO todoDTO = new TodoDTO();
        BeanUtils.copyProperties(todoDO, todoDTO);

        Date dueDate = todoDO.getDueDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        todoDTO.setDueDate(sdf.format(dueDate));

        PriorityEnum priorityEnum = PriorityEnum.getEnum(todoDO.getPriority());
        if (priorityEnum != null) {
            todoDTO.setPriority(priorityEnum.getName());
        }

        StatusEnum statusEnum = StatusEnum.getEnum(todoDO.getStatus());
        if (statusEnum != null) {
            todoDTO.setStatus(statusEnum.getName());
        }

        return todoDTO;
    }

    private TodoDO convertTodoDTO2DO(TodoDTO todoDTO) throws ParseException {
        TodoDO todoDO = new TodoDO();
        BeanUtils.copyProperties(todoDTO, todoDO);

        String dueDate = todoDTO.getDueDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        todoDO.setDueDate(sdf.parse(dueDate));

        PriorityEnum priorityEnum = PriorityEnum.getEnum(todoDTO.getPriority());
        if (priorityEnum != null) {
            todoDO.setPriority(priorityEnum.getIndex());
        }

        StatusEnum statusEnum = StatusEnum.getEnum(todoDTO.getStatus());
        if (statusEnum != null) {
            todoDO.setStatus(statusEnum.getIndex());
        }

        return todoDO;
    }
}
