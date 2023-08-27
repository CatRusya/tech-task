package com.andersen.techtask.repository;

import com.andersen.techtask.dto.CityDto;
import com.andersen.techtask.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {


 //   Page<City> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"cityName", "country"})
    Page<CityDto> findCitiesByCountryId(Long id, Pageable pageable);



//    @EntityGraph(attributePaths = {"country"})
//    Page<City> findAllByName(String city, Pageable pageable);

//    @Query("SELECT DISTINCT c.name FROM City c")
//    Page<String> findAllDistinctName(Pageable pageable);
}
