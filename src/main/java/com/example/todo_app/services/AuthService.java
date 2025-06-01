package com.example.todo_app.services;

import com.example.todo_app.dto.response.UserResponse;
import com.example.todo_app.exception.EntityNotFoundException;
import com.example.todo_app.exception.UserAlreadyExistsException;
import com.example.todo_app.exception.AuthenticationException;
import com.example.todo_app.model.User;
import com.example.todo_app.repository.UserRepository;
import com.example.todo_app.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public UserResponse register(String username, String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new UserAlreadyExistsException("Пользователь с email " + email + " уже существует");
        }

        User user = new User();
        user.setUsernameField(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        User savedUser = userRepository.save(user);
        return new UserResponse(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Пользователь с email " + email + " не найден"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AuthenticationException("Неверный пароль");
        }

        return jwtUtil.generateToken(user.getEmail());
    }

}