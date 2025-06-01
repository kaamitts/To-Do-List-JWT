    package com.example.todo_app.controller;

    import com.example.todo_app.dto.response.UserResponse;
    import com.example.todo_app.model.User;
    import com.example.todo_app.services.ProfileService;
    import jakarta.validation.Valid;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.core.annotation.AuthenticationPrincipal;
    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/profile")
    public class ProfileController {

        private final ProfileService profileService;

        public ProfileController(ProfileService profileService) {
            this.profileService = profileService;
        }

        @GetMapping
        public ResponseEntity<UserResponse> getProfile(@AuthenticationPrincipal User user) {
            UserResponse userResponse = profileService.getProfile(user);
            return ResponseEntity.ok(userResponse);
        }

        @PutMapping
        public ResponseEntity<UserResponse> updateProfile(
                @AuthenticationPrincipal User user,
                @Valid @RequestBody UserResponse updateDto) {
            UserResponse updatedUser = profileService.updateProfile(user, updateDto);
            return ResponseEntity.ok(updatedUser);
        }
    }