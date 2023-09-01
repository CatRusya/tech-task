package com.andersen.techtask.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request for login")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtRequest {

  @Schema(description = "email", example = "johndoe@gmail.com")
  @NotNull(message = "Username must be not null") String username;

  @Schema(description = "email", example = "12345")
  @NotNull(message = "Password must be not null") String password;
}
