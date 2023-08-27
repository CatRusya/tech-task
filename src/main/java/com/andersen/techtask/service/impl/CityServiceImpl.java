package com.andersen.techtask.service.impl;

import com.andersen.techtask.dto.CityDto;
import com.andersen.techtask.dto.CountryDto;
import com.andersen.techtask.entity.City;
import com.andersen.techtask.entity.Country;
import com.andersen.techtask.mappers.CityMapper;
import com.andersen.techtask.mappers.CountryMapper;
import com.andersen.techtask.repository.CityRepository;
import com.andersen.techtask.repository.CountryRepository;
import com.andersen.techtask.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

//    private final CityRepository cityRepository;
//    private final CityMapper cityMapper;
//    @Override
//    public Page<CityDto> getAllCitiesByCountry(Long id, Pageable pageable) {
//
//        var cities = cityRepository.findCitiesByCountryId(id, pageable);
//
//        return cities;
//
//    }

}
