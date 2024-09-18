package org.example.todolistserver.service;

import org.example.todolistserver.general.Result;
import org.example.todolistserver.model.TodoDO;

import java.util.List;

public interface TodoService {
    /**
     * Get the todo list based on sortType(dueDate, priority) and showType(ongoing, finished)
     * @param username
     * @param sortType sort based on: dueDate, priority
     * @param showType ongoing, finished
     * @return current user's todo list
     */
    Result<List<TodoDO>> getTodoList(String username, int sortType, int showType);

    /**
     * Set a todo to finished status
     * @param id
     * @return
     */
    Result<Boolean> finishTodo(int id);

    /**
     * Delete a todo
     * @param id
     * @return
     */
    Result<Boolean> deleteTodo(int id);

    /**
     * Insert a todo
     * @param todoDO
     * @return
     */
    Result<Boolean> insertTodo(TodoDO todoDO);
}
