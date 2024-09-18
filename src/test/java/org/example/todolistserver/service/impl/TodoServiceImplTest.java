package org.example.todolistserver.service.impl;

import org.example.todolistserver.controller.dto.TodoDTO;
import org.example.todolistserver.general.Result;
import org.example.todolistserver.mapper.TodoMapper;
import org.example.todolistserver.model.TodoDO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.Assert;

import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodoServiceImplTest {

    @Mock
    private TodoMapper mockTodoMapper;

    @InjectMocks
    private TodoServiceImpl todoServiceImplUnderTest;

    @Test
    void testGetTodoList() {
        // Setup
        final TodoDTO todoDTO = new TodoDTO();
        todoDTO.setId(0);
        todoDTO.setTitle("title");
        todoDTO.setStatus("name");
        todoDTO.setDueDate("dueDate");
        todoDTO.setPriority("name");
        final Result<List<TodoDTO>> expectedResult = Result.success(List.of(todoDTO));

        // Configure TodoMapper.select(...).
        final TodoDO todoDO = new TodoDO();
        todoDO.setId(0);
        todoDO.setTitle("title");
        todoDO.setStatus(0);
        todoDO.setDueDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        todoDO.setPriority(0);
        todoDO.setOwners("username");
        final List<TodoDO> todoDOList = List.of(todoDO);
        final TodoDO todoDO1 = new TodoDO();
        todoDO1.setId(0);
        todoDO1.setTitle("title");
        todoDO1.setStatus(0);
        todoDO1.setDueDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        todoDO1.setPriority(0);
        todoDO1.setOwners("username");
        when(mockTodoMapper.select(todoDO1)).thenReturn(todoDOList);

        // Run the test
        final Result<List<TodoDTO>> result = todoServiceImplUnderTest.getTodoList("username", 0, 0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetTodoList_TodoMapperReturnsNoItems() {
        // Setup
        // Configure TodoMapper.select(...).
        final TodoDO todoDO = new TodoDO();
        todoDO.setId(0);
        todoDO.setTitle("title");
        todoDO.setStatus(0);
        todoDO.setDueDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        todoDO.setPriority(0);
        todoDO.setOwners("username");
        when(mockTodoMapper.select(todoDO)).thenReturn(Collections.emptyList());

        // Run the test
        final Result<List<TodoDTO>> result = todoServiceImplUnderTest.getTodoList("username", 0, 0);

        // Verify the results
        assertThat(result).isEqualTo(Result.success(Collections.emptyList()));
    }

    @Test
    void testFinishTodo() {

        // Configure TodoMapper.selectById(...).
        final TodoDO todoDO = new TodoDO();
        todoDO.setId(0);
        todoDO.setTitle("title");
        todoDO.setStatus(0);
        todoDO.setDueDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        todoDO.setPriority(0);
        todoDO.setOwners("username");
        final List<TodoDO> todoDOList = List.of(todoDO);
        when(mockTodoMapper.selectById(0)).thenReturn(todoDOList);

        // Configure TodoMapper.update(...).
        final TodoDO todoDO1 = new TodoDO();
        todoDO1.setId(0);
        todoDO1.setTitle("title");
        todoDO1.setStatus(0);
        todoDO1.setDueDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        todoDO1.setPriority(0);
        todoDO1.setOwners("username");
        when(mockTodoMapper.update(todoDO1)).thenReturn(0);

        // Run the test
        final Result<Boolean> result = todoServiceImplUnderTest.finishTodo(0);

        // Verify the results
        Assert.isTrue(result.getSuccess(), "");
    }

    @Test
    void testFinishTodo_TodoMapperSelectByIdReturnsNull() {
        // Setup
        when(mockTodoMapper.selectById(0)).thenReturn(null);

        // Run the test
        final Result<Boolean> result = todoServiceImplUnderTest.finishTodo(0);

        // Verify the results
        assertThat(result).isEqualTo(Result.success());
    }

    @Test
    void testFinishTodo_TodoMapperSelectByIdReturnsNoItems() {
        // Setup
        when(mockTodoMapper.selectById(0)).thenReturn(Collections.emptyList());

        // Run the test
        final Result<Boolean> result = todoServiceImplUnderTest.finishTodo(0);

        // Verify the results
        Assert.isTrue(result.getSuccess(), "");
    }

    @Test
    void testDeleteTodo() {

        // Configure TodoMapper.selectById(...).
        final TodoDO todoDO = new TodoDO();
        todoDO.setId(0);
        todoDO.setTitle("title");
        todoDO.setStatus(0);
        todoDO.setDueDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        todoDO.setPriority(0);
        todoDO.setOwners("username");
        final List<TodoDO> todoDOList = List.of(todoDO);
        when(mockTodoMapper.selectById(0)).thenReturn(todoDOList);

        when(mockTodoMapper.deleteById(0)).thenReturn(0);

        // Run the test
        final Result<Boolean> result = todoServiceImplUnderTest.deleteTodo(0);

        // Verify the results
        Assert.isTrue(result.getSuccess(), "");
    }

    @Test
    void testDeleteTodo_TodoMapperSelectByIdReturnsNull() {
        // Setup
        when(mockTodoMapper.selectById(0)).thenReturn(null);

        // Run the test
        final Result<Boolean> result = todoServiceImplUnderTest.deleteTodo(0);

        // Verify the results
        assertThat(result).isEqualTo(Result.success());
    }

    @Test
    void testDeleteTodo_TodoMapperSelectByIdReturnsNoItems() {
        // Setup
        when(mockTodoMapper.selectById(0)).thenReturn(Collections.emptyList());

        // Run the test
        final Result<Boolean> result = todoServiceImplUnderTest.deleteTodo(0);

        // Verify the results
        Assert.isTrue(result.getSuccess(), "");
    }

    @Test
    void testInsertOrUpdateTodo() {
        // Setup
        final TodoDTO todoDTO = new TodoDTO();
        todoDTO.setId(0);
        todoDTO.setTitle("title");
        todoDTO.setStatus("name");
        todoDTO.setDueDate("dueDate");
        todoDTO.setPriority("name");

        // Configure TodoMapper.selectById(...).
        final TodoDO todoDO = new TodoDO();
        todoDO.setId(0);
        todoDO.setTitle("title");
        todoDO.setStatus(0);
        todoDO.setDueDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        todoDO.setPriority(0);
        todoDO.setOwners("username");
        final List<TodoDO> todoDOList = List.of(todoDO);
        when(mockTodoMapper.selectById(0)).thenReturn(todoDOList);

        // Configure TodoMapper.update(...).
        final TodoDO todoDO1 = new TodoDO();
        todoDO1.setId(0);
        todoDO1.setTitle("title");
        todoDO1.setStatus(0);
        todoDO1.setDueDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        todoDO1.setPriority(0);
        todoDO1.setOwners("username");
        when(mockTodoMapper.update(todoDO1)).thenReturn(0);

        // Run the test
        final Result<Boolean> result = todoServiceImplUnderTest.insertOrUpdateTodo(todoDTO);

        // Verify the results
        Assert.isTrue(result.getSuccess(), "");
    }

    @Test
    void testInsertOrUpdateTodo_TodoMapperSelectByIdReturnsNull() {
        // Setup
        final TodoDTO todoDTO = new TodoDTO();
        todoDTO.setId(0);
        todoDTO.setTitle("title");
        todoDTO.setStatus("name");
        todoDTO.setDueDate("dueDate");
        todoDTO.setPriority("name");

        when(mockTodoMapper.selectById(0)).thenReturn(null);

        // Configure TodoMapper.insert(...).
        final TodoDO todoDO = new TodoDO();
        todoDO.setId(0);
        todoDO.setTitle("title");
        todoDO.setStatus(0);
        todoDO.setDueDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        todoDO.setPriority(0);
        todoDO.setOwners("username");
        when(mockTodoMapper.insert(todoDO)).thenReturn(0);

        // Run the test
        final Result<Boolean> result = todoServiceImplUnderTest.insertOrUpdateTodo(todoDTO);

        // Verify the results
        assertThat(result).isEqualTo(Result.success());
    }

    @Test
    void testInsertOrUpdateTodo_TodoMapperSelectByIdReturnsNoItems() {
        // Setup
        final TodoDTO todoDTO = new TodoDTO();
        todoDTO.setId(0);
        todoDTO.setTitle("title");
        todoDTO.setStatus("name");
        todoDTO.setDueDate("dueDate");
        todoDTO.setPriority("name");

        when(mockTodoMapper.selectById(0)).thenReturn(Collections.emptyList());

        // Configure TodoMapper.insert(...).
        final TodoDO todoDO = new TodoDO();
        todoDO.setId(0);
        todoDO.setTitle("title");
        todoDO.setStatus(0);
        todoDO.setDueDate(new GregorianCalendar(2020, Calendar.JANUARY, 1).getTime());
        todoDO.setPriority(0);
        todoDO.setOwners("username");
        when(mockTodoMapper.insert(todoDO)).thenReturn(0);

        // Run the test
        final Result<Boolean> result = todoServiceImplUnderTest.insertOrUpdateTodo(todoDTO);

        // Verify the results
        Assert.isTrue(result.getSuccess(), "");
    }
}
