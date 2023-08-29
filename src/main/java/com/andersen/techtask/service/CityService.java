package com.andersen.techtask.service;

import com.andersen.techtask.dto.CityDto;
import com.andersen.techtask.dto.response.CityResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CityService {
    CityResponse getAllCities(int page, int size, String cityName, String countryName);

    CityDto editCityName(Long id, CityDto dto);


}
