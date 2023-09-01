package com.andersen.techtask.controller;

import com.andersen.techtask.dto.UserDto;
import com.andersen.techtask.dto.auth.JwtRequest;
import com.andersen.techtask.dto.auth.JwtResponse;
import com.andersen.techtask.entity.User;
import com.andersen.techtask.mappers.UserMapper;
import com.andersen.techtask.service.AuthService;
import com.andersen.techtask.service.UserService;
import com.andersen.techtask.validation.OnCreate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Validated
@Tag(name = "Auth Controller", description = "Auth API")
public class AuthController {

  private final AuthService authService;
  private final UserService userService;
  private final UserMapper userMapper;

  @PostMapping("/login")
  @Operation(summary = "Login method")
  public ResponseEntity <JwtResponse> login(@Validated @RequestBody JwtRequest loginRequest) {
    log.info("Log in request in customer-service. Customer email: {}", loginRequest.getUsername());
    return ResponseEntity.ok(authService.login(loginRequest));
  }

  @PostMapping("/register")
  @Operation(summary = "Register method")
  public ResponseEntity <UserDto> register(@Validated(OnCreate.class) @RequestBody UserDto userDto) {
    log.info("Log in 'registration' method in auth-controller");
    User user = userMapper.toEntity(userDto);
    User createdUser = userService.create(user);
    return ResponseEntity.ok(userMapper.toDto(createdUser));
  }

  @PostMapping("/refresh")
  @Operation(summary = "Method for getting new access-token.")
  public ResponseEntity <JwtResponse> refresh(@RequestBody String refreshToken) {
    log.info("Token refresh 'request' in auth-controller");
    return ResponseEntity.ok(authService.refresh(refreshToken));
  }
}
