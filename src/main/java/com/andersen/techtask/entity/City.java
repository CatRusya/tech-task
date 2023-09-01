package com.andersen.techtask.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class City implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  Long id;

  @Column(name = "city_name")
  String cityName;

  @ManyToOne
  @JoinColumn(name = "country_id", referencedColumnName = "id")
  @OnDelete(action = OnDeleteAction.CASCADE)
  Country country;

}
