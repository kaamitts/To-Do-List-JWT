package com.example.todo_app.controller;

import com.example.todo_app.dto.request.TodoRequest;
import com.example.todo_app.dto.response.TodoResponse;
import com.example.todo_app.enumiration.Status;
import com.example.todo_app.model.User;
import com.example.todo_app.services.TodoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<TodoResponse> createTodo(
            @Valid @RequestBody TodoRequest request,
            @AuthenticationPrincipal User user
    ) {
        TodoResponse todoResponse = todoService.createTodo(request, user);
        return ResponseEntity.ok(todoResponse);
    }

    @GetMapping
    public ResponseEntity<List<TodoResponse>> getAllTodos(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false) Status status
    ) {
        List<TodoResponse> todos = todoService.getAllTodos(user, status);
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoResponse> getTodoById(
            @PathVariable long id,
            @AuthenticationPrincipal User user
    ) {
        TodoResponse todoResponse = todoService.getTodoById(id, user);
        return ResponseEntity.ok(todoResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoResponse> updateTodo(
            @PathVariable long id,
            @Valid @RequestBody TodoRequest request,
            @AuthenticationPrincipal User user
    ) {
        TodoResponse todoResponse = todoService.updateTodo(id, request, user);
        return ResponseEntity.ok(todoResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodo(
            @PathVariable Long id,
            @AuthenticationPrincipal User user
    ) {
        todoService.deleteTodo(id, user);
        return ResponseEntity.noContent().build();
    }
}
