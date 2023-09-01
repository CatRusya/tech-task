package com.andersen.techtask.dto.auth;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtResponse {

  Long id;
  String username;
  String accessToken;
  String refreshToken;
}
