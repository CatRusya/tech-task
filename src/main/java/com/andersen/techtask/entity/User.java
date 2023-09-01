package com.andersen.techtask.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;
import lombok.*;

@Table(name = "users")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  Long id;

  @Column(name = "name")
  private String name;
  @Column(name = "username")
  private String username;
  @Column(name = "password")
  private String password;

  @Transient
  private String passwordConfirmation;


  @Column(name = "role")
  @ElementCollection(fetch = FetchType.EAGER)
  @CollectionTable(name = "user_role")
  @Enumerated(value = EnumType.STRING)
  private Set<Role> roles;

}
