package org.example.todolistserver.service;

import org.example.todolistserver.mapper.UserMapper;
import org.mockito.Mockito;

import java.lang.reflect.Field;

public class TestUtil {

    public UserMapper init(Object classInstance){
        // 要模拟的类
        UserMapper em = Mockito.mock(UserMapper.class);
        // 指定反射类
        Class<?> clazz = classInstance.getClass();
        // 获得指定类的属性
        Field field = null;
        try {
            field = clazz.getDeclaredField("em");
            field.setAccessible(true);
            // 更改私有属性的值
            field.set(classInstance, em);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return em;
    }
}
