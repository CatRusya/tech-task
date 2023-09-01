package com.andersen.techtask.service;

import com.andersen.techtask.dto.auth.JwtRequest;
import com.andersen.techtask.dto.auth.JwtResponse;

public interface AuthService {

  JwtResponse login(JwtRequest loginRequest);

  JwtResponse refresh(String refreshToken);

}
