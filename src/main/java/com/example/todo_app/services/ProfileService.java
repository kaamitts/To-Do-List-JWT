package com.example.todo_app.services;

import com.example.todo_app.dto.response.UserResponse;
import com.example.todo_app.model.User;
import com.example.todo_app.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final UserRepository userRepository;

    public ProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse updateProfile(User user, UserResponse updateDto) {
//        if (updateDto.getEmail() != null && !updateDto.getEmail().equals(user.getEmail())) {
//            userRepository.findByEmail(updateDto.getEmail())
//                    .ifPresent(_ -> {
//                        throw new IllegalArgumentException("Email already exists");
//                    });
//            user.setEmail(updateDto.getEmail());
//        }

        if (updateDto.getUsername() != null) {
            user.setUsernameField(updateDto.getUsername());
        }

        User updatedUser = userRepository.save(user);

        return new UserResponse(updatedUser.getId(), updatedUser.getUsernameField(), updatedUser.getEmail());
    }

    public UserResponse getProfile(User user) {
        return new UserResponse(user.getId(), user.getUsernameField(), user.getEmail());
    }
}