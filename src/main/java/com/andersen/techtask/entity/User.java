package com.andersen.techtask.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table(name = "users")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  Long id;

  @Column(name = "name")
  String name;
  @Column(name = "username")
  String username;
  @Column(name = "password")
  String password;

  @Transient
  private String passwordConfirmation;

  @Column(name = "role")
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "user_role")
  @Enumerated(value = EnumType.STRING)
  Set<Role> roles;

}
