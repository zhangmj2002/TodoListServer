package org.example.todolistserver.service;

import org.example.todolistserver.general.Result;
import org.example.todolistserver.model.UserDO;

public interface UserService {
    /**
     * auth the user based on username and password
     * @param userName
     * @param password
     * @return success or not
     */
    Result<UserDO> authUser(String userName, String password);

    /**
     * Actually insert the user's info
     * @param userDO
     * @return success or not
     */
    Result<String> registerUser(UserDO userDO);
}
