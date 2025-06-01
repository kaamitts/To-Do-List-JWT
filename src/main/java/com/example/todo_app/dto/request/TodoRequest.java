package com.example.todo_app.dto.request;

import com.example.todo_app.enumiration.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TodoRequest {
    @NotBlank(message = "Заголовок не может быть пустым")
    @Size(max = 255, message = "Заголовок не может быть длиннее 255 символов")
    private String title;

    private String description;

    @NotNull(message = "Статус задачи не может быть пустым")
    private Status status;

    public TodoRequest() {
    }

    public TodoRequest(String title, String description, Status status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TodoRequest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
