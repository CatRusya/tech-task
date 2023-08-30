package com.andersen.techtask.service.impl;


import com.andersen.techtask.dto.CityDto;
import com.andersen.techtask.dto.response.CityResponse;
import com.andersen.techtask.entity.City;
import com.andersen.techtask.repository.CityRepository;
import com.andersen.techtask.service.CityService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;


@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    @Value("${minio.url}")
    private String imageUrl;

    @Value("${minio.bucket}")
    private String imageBucket;


    private final CityRepository cityRepository;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "CityService::getById", key = "#id")
    public City getCityById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cty not found"));
    }

    @Override
    @Transactional(readOnly = true)
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
    }

    @Override
    @Transactional
    @Caching(put = {
            @CachePut(value = "CityService::getById", key = "#city.id"),
    })
    public CityDto editCityName(Long id, CityDto dto) {
        City city = getCityById(id);
        city.setCityName(dto.getCityName());
        cityRepository.save(city);
        return new CityDto(city.getId(), city.getCityName(), city.getCountry().getCountryName(), city.getCountry().getLogo());
    }

    private CityDto cityToDto(City city) {
        return new CityDto(
                city.getId(),
                city.getCityName(),
                city.getCountry().getCountryName(),
                getFlagUrl(city.getCountry().getLogo()));
    }


    private String getFlagUrl(String flagPath) {
        if (Objects.isNull(flagPath))
            return null;
        return imageUrl + "/" + imageBucket + "/" + flagPath;
    }

}
