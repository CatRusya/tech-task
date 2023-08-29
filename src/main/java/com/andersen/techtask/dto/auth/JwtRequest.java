package com.andersen.techtask.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Schema (description = "Request for login")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtRequest {

    @Schema (description = "email", example = "johndoe@gmail.com")
    @NotNull(message = "Username must be not null")
    String username;

    @Schema (description = "email", example = "12345")
    @NotNull(message = "Password must be not null")
    String password;
}
