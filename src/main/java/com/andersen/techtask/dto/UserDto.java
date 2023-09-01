package com.andersen.techtask.dto;

import com.andersen.techtask.validation.OnCreate;
import com.andersen.techtask.validation.OnUpdate;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@Schema(description = "User DTO")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto implements Serializable {

  @Schema(description = "User 1", example = "1")
  @NotNull(message = "Id must be not null", groups = OnUpdate.class)
  Long id;

  @Schema(description = "User name", example = "John Doe")
  @NotNull(message = "Name must be not null", groups = {OnCreate.class, OnUpdate.class}) @Length(max = 255, message = "User name must be smaller than 255 symbols",
      groups = {OnCreate.class, OnUpdate.class})
  String name;

  @Schema(description = "User email", example = "johndoe@gmail.com")
  @NotNull(message = "Username must be not null", groups = {OnCreate.class, OnUpdate.class}) @Length(max = 255, message = "Username must be smaller than 255 symbols",
      groups = {OnCreate.class, OnUpdate.class})
  String username;


  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @NotNull(message = "Password must be not null", groups = {OnCreate.class, OnUpdate.class}) String password;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  @NotNull(message = "Password confirmation must be not null.",
      groups = {OnCreate.class})
  private String passwordConfirmation;

}
