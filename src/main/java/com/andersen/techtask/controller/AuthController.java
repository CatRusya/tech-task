package com.andersen.techtask.controller;

import com.andersen.techtask.dto.UserDto;
import com.andersen.techtask.dto.auth.JwtRequest;
import com.andersen.techtask.dto.auth.JwtResponse;
import com.andersen.techtask.entity.User;
import com.andersen.techtask.mappers.UserMapper;
import com.andersen.techtask.service.AuthService;
import com.andersen.techtask.service.UserService;
import com.andersen.techtask.validation.OnCreate;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
  public ResponseEntity <JwtResponse> login(@Validated @RequestBody JwtRequest loginRequest) {
    return ResponseEntity.ok(authService.login(loginRequest));
  }

  @PostMapping("/register")
  public UserDto register(@Validated(OnCreate.class) @RequestBody UserDto userDto) {
    User user = userMapper.toEntity(userDto);
    User createdUser = userService.create(user);
    return userMapper.toDto(createdUser);
  }

  @PostMapping("/refresh")
  public JwtResponse refresh(@RequestBody String refreshToken) {
    return authService.refresh(refreshToken);
  }
}
