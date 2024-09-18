package org.example.todolistserver.controller.dto;

import lombok.Data;

@Data
public class TodoDTO {
    private int id;
    private String title;
    private String description;
    private String status;
    private String dueDate;
    private String priority;
    private String tag;
    private String owners;

    @Override
    public String toString() {
        return "TodoDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", dueDate='" + dueDate + '\'' +
                ", priority=" + priority +
                ", tag='" + tag + '\'' +
                ", owners='" + owners + '\'' +
                '}';
    }
}
