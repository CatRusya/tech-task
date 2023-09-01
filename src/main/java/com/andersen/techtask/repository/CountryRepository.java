package com.andersen.techtask.repository;

import com.andersen.techtask.entity.Country;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

  @EntityGraph(attributePaths = {"cities"})
  Page<Country> findAll(@NotNull Pageable pageable);
}
