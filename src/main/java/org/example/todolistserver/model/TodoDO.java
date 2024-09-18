package org.example.todolistserver.model;

import lombok.Data;

import java.util.Date;

@Data
public class TodoDO {
    private int id;
    private String title;
    private String description;
    private Integer status;
    private Date dueDate;
    private Integer priority;
    private String tag;
    private String owners;

    @Override
    public String toString() {
        return "TodoDO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", dueDate=" + dueDate +
                ", priority=" + priority +
                ", tag='" + tag + '\'' +
                ", owners='" + owners + '\'' +
                '}';
    }
}
