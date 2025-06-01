package com.example.todo_app.services;

import com.example.todo_app.dto.request.TodoRequest;
import com.example.todo_app.dto.response.TodoResponse;
import com.example.todo_app.enumiration.Status;
import com.example.todo_app.exception.EntityNotFoundException;
import com.example.todo_app.model.Todo;
import com.example.todo_app.model.User;
import com.example.todo_app.repository.TodoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public TodoResponse createTodo(TodoRequest request, User user) {
        if (request.getTitle() == null) {
            throw new IllegalArgumentException("Title cannot be null");
        }
        if (request.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }

        Todo todo = new Todo();
        todo.setTitle(request.getTitle());
        todo.setDescription(request.getDescription());
        todo.setStatus(Status.TODO);
        todo.setCreatedAt(LocalDateTime.now());
        todo.setUpdatedAt(LocalDateTime.now());

        todo.setUser(user);
        Todo savedTodo = todoRepository.save(todo);

        return new TodoResponse(
                savedTodo.getId(),
                savedTodo.getTitle(),
                savedTodo.getDescription(),
                savedTodo.getStatus(),
                savedTodo.getCreatedAt(),
                savedTodo.getUpdatedAt(),
                savedTodo.getUser().getId()
        );
    }


    public List<TodoResponse> getAllTodos(User user, Status status) {
        List<Todo> todos;
        if (status != null) {
            todos = todoRepository.findByUserAndStatus(user, status);
        } else {
            todos = todoRepository.findByUser(user);
        }

        return todos.stream()
                .map(todo -> new TodoResponse(
                        todo.getId(),
                        todo.getTitle(),
                        todo.getDescription(),
                        todo.getStatus(),
                        todo.getCreatedAt(),
                        todo.getUpdatedAt(),
                        todo.getUser().getId()
                ))
                .collect(Collectors.toList());
    }

    public TodoResponse getTodoById(Long id, User user) {
        Todo todo = todoRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new EntityNotFoundException("Task with ID " + id + " not found for user"));

        return new TodoResponse(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.getStatus(),
                todo.getCreatedAt(),
                todo.getUpdatedAt(),
                todo.getUser().getId()
        );
    }

    public TodoResponse updateTodo(Long id, TodoRequest request, User user) {
        Todo todo = todoRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new EntityNotFoundException("Task with ID " + id + " not found for user"));

        if (request.getTitle() != null) {
            todo.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            todo.setDescription(request.getDescription());
        }
        if (request.getStatus() != null) {
            todo.setStatus(request.getStatus());
        }
        todo.setUpdatedAt(LocalDateTime.now());

        Todo updatedTodo = todoRepository.save(todo);

        return new TodoResponse(
                updatedTodo.getId(),
                updatedTodo.getTitle(),
                updatedTodo.getDescription(),
                updatedTodo.getStatus(),
                updatedTodo.getCreatedAt(),
                updatedTodo.getUpdatedAt(),
                updatedTodo.getUser().getId()
        );
    }

    public void deleteTodo(Long id, User user) {
        Todo todo = todoRepository.findByIdAndUser(id, user)
                .orElseThrow(() -> new EntityNotFoundException("Task not fount"));

        todoRepository.delete(todo);
    }
}