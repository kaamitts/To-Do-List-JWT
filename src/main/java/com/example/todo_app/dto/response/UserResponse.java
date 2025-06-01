package com.example.todo_app.dto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UserResponse {
    private Long id;
    @Size(min = 3, message = "Username must be at least 3 characters long")
    private String username;

    @Email(message = "Email must be a valid email address")
    private String email;

    public UserResponse() {
    }

    public UserResponse(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
