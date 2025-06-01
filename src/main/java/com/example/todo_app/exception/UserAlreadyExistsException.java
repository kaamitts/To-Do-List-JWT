package com.example.todo_app.exception;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
