package org.example.todolistserver.controller;

import org.example.todolistserver.controller.dto.TodoDTO;
import org.example.todolistserver.general.Result;
import org.example.todolistserver.service.impl.TodoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Allow CORS requests from React on port 3000 (change if needed)
public class TodoController {

    @Autowired
    private TodoServiceImpl todoService;

    @GetMapping("/getTodos")
    public ResponseEntity<List<TodoDTO>> getTodos(
            @RequestParam String username, @RequestParam int sortType, @RequestParam int showType) {

        Result<List<TodoDTO>> result = todoService.getTodoList(username, sortType, showType);
        if (! result.getSuccess()) {
            System.out.println(result.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }

        System.out.println(result.getData());
        return new ResponseEntity<>(result.getData(), HttpStatus.OK);
    }

    @GetMapping("/finishTodo")
    public ResponseEntity<Boolean> finishTodo(@RequestParam int id) {

        Result<Boolean> result = todoService.finishTodo(id);
        if (! result.getSuccess()) {
            System.out.println(result.getMessage());
            return new ResponseEntity<>(false, HttpStatus.NOT_MODIFIED);
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @GetMapping("/deleteTodo")
    public ResponseEntity<Boolean> deleteTodo(@RequestParam int id) {

        Result<Boolean> result = todoService.deleteTodo(id);
        if (! result.getSuccess()) {
            System.out.println(result.getMessage());
            return new ResponseEntity<>(false, HttpStatus.NOT_MODIFIED);
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @PostMapping("/insertOrUpdateTodo")
    public ResponseEntity<Boolean> insertOrUpdateTodo(@RequestBody TodoDTO todoDTO) {

        Result<Boolean> result = todoService.insertOrUpdateTodo(todoDTO);
        if (! result.getSuccess()) {
            System.out.println(result.getMessage());
            return new ResponseEntity<>(false, HttpStatus.NOT_MODIFIED);
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
