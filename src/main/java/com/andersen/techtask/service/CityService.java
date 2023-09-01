package com.andersen.techtask.service;

import com.andersen.techtask.dto.CityDto;
import com.andersen.techtask.dto.response.CityResponse;
import com.andersen.techtask.entity.City;

public interface CityService {
  CityResponse getAllCities(int page, int size, String cityName, String countryName);

  CityDto editCityName(Long id, CityDto dto);

  City getCityById(Long id);


}
