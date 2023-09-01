package com.andersen.techtask.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Table(name = "country")
@Entity(name = "country")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Country implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  Long id;

  @Column(name = "country_name")
  String countryName;

  @Column(name = "logo")
  @CollectionTable(name = "country_logo")
  String logo;

  @OneToMany(mappedBy = "country", cascade = {
      CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  private List<City> cities;

}
