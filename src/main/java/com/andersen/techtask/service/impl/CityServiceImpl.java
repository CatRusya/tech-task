package com.andersen.techtask.service.impl;


import com.andersen.techtask.dto.CityDto;
import com.andersen.techtask.dto.response.CityResponse;
import com.andersen.techtask.entity.City;
import com.andersen.techtask.mappers.CityMapper;
import com.andersen.techtask.repository.CityRepository;
import com.andersen.techtask.service.CityService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public CityResponse getAllCities(int page, int size, String cityName, String countryName) {
        Page<City> pageCities;

        if (countryName != null) {
            pageCities = cityRepository.findCitiesByCountryName(countryName, PageRequest.of(page, size));
        } else if (cityName != null) {
            pageCities = cityRepository.findAllByCityName(cityName, PageRequest.of(page, size));
        } else {
            pageCities = cityRepository.findAll(PageRequest.of(page, size));
        }

        Page<CityDto> pageCityDtos = pageCities.map(this::cityToDto);
        return new CityResponse(PageRequest.of(page, size), pageCityDtos.getContent());
//        if (countryName != null) {
//            Page<City> pageCities = cityRepository.findCitiesByCountryName(countryName, PageRequest.of(page, size));
//            Page<CityDto> pageCityDtos = pageCities.map(this::cityToDto);
//            return new CityResponse(PageRequest.of(page, size), pageCityDtos.getContent());
//        }
//        if (cityName != null) {
//            Page<City> pageCities = cityRepository.findAllByCityName(cityName, PageRequest.of(page, size));
//            Page<CityDto> pageCityDtos = pageCities.map(this::cityToDto);
//            return new CityResponse(PageRequest.of(page, size), pageCityDtos.getContent());
//        }
//
//        Page<City> pageCities = cityRepository.findAll(PageRequest.of(page, size));
//        Page<CityDto> pageCityDtos = pageCities.map(this::cityToDto);
//        return new CityResponse(PageRequest.of(page, size), pageCityDtos.getContent());
    }

    @Override
    public CityDto editCityName(Long id, CityDto dto) {
        City city = cityRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException());
        city.setCityName(dto.getCityName());
        cityRepository.save(city);
        CityDto editCityName = new CityDto(city.getId(), city.getCityName(), city.getCountry().getCountryName(), city.getCountry().getLogo());
        return editCityName;
    }


    private CityDto cityToDto(City city) {
        return new CityDto(
                city.getId(),
                city.getCityName(),
                city.getCountry().getCountryName(),
                getFlagUrl(city.getCountry().getLogo()));
    }


    private String getFlagUrl(String flagPath) {
        return null;
//        if (Objects.isNull(flagPath))
//            return null;
//        return IMAGE_HOST + "/" + FLAGS_BUCKET + "/" + flagPath;
    }

}
