package org.example.todolistserver.service.impl;

import jakarta.annotation.Resource;
import org.example.todolistserver.general.Result;
import org.example.todolistserver.mapper.UserMapper;
import org.example.todolistserver.model.UserDO;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl {

    @Resource
    private UserMapper userMapper;

    /**
     * auth the user based on username and password
     * @param userName
     * @param password
     * @return success or not
     */
    public Result<UserDO> authUser(String userName, String password) {
        UserDO userDO = null;
        try {
            userDO = userMapper.select(userName);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
            return Result.error(e.getMessage());
        }

        if (userDO == null) {
            return Result.error("User not found");
        }

        if (!userDO.getPassword().equals(password)) {
            return Result.error("Wrong password");
        }

        return Result.success(userDO);
    }

    /**
     * Actually insert the user's info
     * @param userDO
     * @return success or not
     */
    public Result<String> registerUser(UserDO userDO) {
        try {
            UserDO existUser = userMapper.select(userDO.getName());
            if (existUser != null) {
                return Result.error("User name already exists");
            }

            if (userMapper.insert(userDO) == 1) {
                return Result.success("User registered successfully");
            } else {
                return Result.error("Insert failed");
            }
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
}
