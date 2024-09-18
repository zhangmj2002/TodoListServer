package org.example.todolistserver.service.impl;

import org.example.todolistserver.general.Result;
import org.example.todolistserver.mapper.UserMapper;
import org.example.todolistserver.model.UserDO;
import org.example.todolistserver.service.TestUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    private final UserServiceImpl userService = new UserServiceImpl();
    private final TestUtil testUtil = new TestUtil();

    @Test
    void registerUserTest() {

        UserDO user = new UserDO();
        int id = (int)(10000 + 100 * Math.random());
        user.setId(id);
        user.setName("test" + id);
        user.setPassword("test");

        UserMapper em =  testUtil.init(userService);
        Mockito.doReturn(user).when(em).select(Mockito.anyString());
        Mockito.doReturn(1).when(em).insert(Mockito.any());

        Result<String> result1 = userService.registerUser(user);
        Assert.isTrue(result1.getSuccess(), "");
    }

    @Test
    void authUserTest() {
        UserDO user = new UserDO();
        int id = (int)(10000 + 100 * Math.random());
        user.setId(id);
        user.setName("test" + id);
        user.setPassword("test");

        UserMapper em =  testUtil.init(userService);
        Mockito.doReturn(user).when(em).select(Mockito.anyString());
        Mockito.doReturn(1).when(em).insert(Mockito.any());

        Result<UserDO> result2 = userService.authUser(user.getName(), "test");
        Assert.notNull(result2.getData(), "");
    }
}
