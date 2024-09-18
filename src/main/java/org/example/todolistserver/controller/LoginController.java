package org.example.todolistserver.controller;


import org.example.todolistserver.controller.dto.LoginDTO;
import org.example.todolistserver.model.UserDO;
import org.example.todolistserver.general.Result;
import org.example.todolistserver.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Allow CORS requests from React on port 3000 (change if needed)
public class LoginController {

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginDTO loginDTO) {
        Result<UserDO> result = userService.authUser(loginDTO.getUsername(), loginDTO.getPassword());
        if (!result.getSuccess()) {
            System.out.println(result.getMessage());
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        }

        System.out.println(result.getData().getName() + " logged in");
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
