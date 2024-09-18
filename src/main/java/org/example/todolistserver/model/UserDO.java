package org.example.todolistserver.model;

import lombok.Data;

@Data
public class UserDO {
    int id;
    String name;
    String password;

    @Override
    public String toString() {
        return "UserDO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
