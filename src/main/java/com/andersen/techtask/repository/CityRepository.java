package com.andersen.techtask.repository;

import com.andersen.techtask.entity.City;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

  @Query("SELECT c FROM City c WHERE c.country.countryName = :countryName")
  Page<City> findCitiesByCountryName(@Param("countryName") String country, Pageable pageable);

  @EntityGraph(attributePaths = {"country"})
  Page<City> findAll(@NotNull Pageable pageable);

  @EntityGraph(attributePaths = {"country"})
  Page<City> findAllByCityName(String cityName, Pageable pageable);

}
