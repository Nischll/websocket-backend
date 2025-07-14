package com.websocket.controller;

import com.websocket.dto.AuthResponse;
import com.websocket.dto.SigninRequest;
import com.websocket.dto.SignupRequest;
import com.websocket.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignupRequest request) {
        authService.signup(request);
        return ResponseEntity.ok("User Registered Successfully");
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody SigninRequest request) {
        AuthResponse response = authService.signin(request);
        return ResponseEntity.ok(response);
    }
}
