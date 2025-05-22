package com.example.authentication.api;

import com.example.authentication.dto.RegisterRequest;
import com.example.authentication.dto.UserResponse;
import com.example.authentication.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        userService.register(request.getUsername(), request.getPassword(), request.getRoles());
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
       return ResponseEntity.ok(userService.getAllUsers());
    }
}
