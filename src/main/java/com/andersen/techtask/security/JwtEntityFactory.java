package com.andersen.techtask.security;

import com.andersen.techtask.entity.Role;
import com.andersen.techtask.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class JwtEntityFactory {

  public static JwtEntity create(User user) {
    return new JwtEntity(
        user.getId(),
        user.getUsername(),
        user.getName(),
        user.getPassword(),
        mapToGrantAuthorities(new ArrayList<>(user.getRoles())));
  }

  private static List<GrantedAuthority> mapToGrantAuthorities(
      List<Role> roles) {
    return roles.stream()
        .map(Enum::name)
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }

}
