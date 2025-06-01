package com.example.todo_app.controller;

import com.example.todo_app.dto.request.LoginRequest;
import com.example.todo_app.dto.request.RegisterRequest;
import com.example.todo_app.dto.response.ErrorResponse;
import com.example.todo_app.dto.response.UserResponse;
import com.example.todo_app.exception.AuthenticationException;
import com.example.todo_app.exception.UserAlreadyExistsException;
import com.example.todo_app.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            String token = authService.login(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(new ErrorResponse(e.getMessage(), 401));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        try {
            UserResponse user = authService.register(
                    registerRequest.getUsername(),
                    registerRequest.getEmail(),
                    registerRequest.getPassword()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new ErrorResponse(e.getMessage(), 409));
        }
    }
}
