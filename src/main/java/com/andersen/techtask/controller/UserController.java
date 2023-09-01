package com.andersen.techtask.controller;

import com.andersen.techtask.dto.UserDto;
import com.andersen.techtask.entity.User;
import com.andersen.techtask.mappers.UserMapper;
import com.andersen.techtask.service.UserService;
import com.andersen.techtask.validation.OnUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
@Tag(name = "User Controller", description = "User API")
public class UserController {

  private final UserService userService;

  private final UserMapper userMapper;

  @PutMapping
  @Operation(summary = "Update user")
  @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
  public ResponseEntity<UserDto> update(@Validated(OnUpdate.class) @RequestBody UserDto dto) {
    log.info("Log in method 'update' in user service");
    User user = userMapper.toEntity(dto);
    User updatedUser = userService.update(user);
    return ResponseEntity.ok(userMapper.toDto(updatedUser));
  }

  @GetMapping("/{id}")
  @Operation(summary = "Get UserDto by id")
  public ResponseEntity <UserDto> getById(@PathVariable Long id) {
    log.info("Log in method 'getById' in user service");
    User user = userService.getById(id);
    return ResponseEntity.ok(userMapper.toDto(user));
  }

  @DeleteMapping("/{id}")
  @Operation(summary = "Delete user by id")
  @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
  public void deleteById(@PathVariable Long id) {
    log.info("Log in method 'deleteById' in user service");
    userService.delete(id);
  }

}
