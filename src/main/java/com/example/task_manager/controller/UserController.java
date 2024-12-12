package com.example.task_manager.controller;

import com.example.task_manager.config.JwtUtil;
import com.example.task_manager.entity.AuthResponse;
import com.example.task_manager.entity.User;
import com.example.task_manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> addNewUser(@RequestBody User user){

        if(userService.findUser(user.getUsername()).isPresent())
            return ResponseEntity.badRequest().body(Map.of("message", "Username already exists"));

        User newUser = userService.addNewUser(user);
        return  ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){

        Optional<User> existingUser = userService.findUser(user.getUsername());
        if(existingUser==null || !new BCryptPasswordEncoder().matches(user.getPassword(), existingUser.get().getPassword()))
            return ResponseEntity.badRequest().body("Incorrect username/password");

        String token = jwtUtil.generateToken(user.getUsername());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
