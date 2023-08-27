package com.andersen.techtask.service.impl;

import com.andersen.techtask.dto.CountryDto;
import com.andersen.techtask.entity.Country;
import com.andersen.techtask.mappers.CountryMapper;
import com.andersen.techtask.repository.CountryRepository;
import com.andersen.techtask.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    private final CountryMapper countryMapper;


    @Override
    public Page<CountryDto> getAllCountries(Pageable pageable) {
        Page<Country> pageCountries = countryRepository.findAll(pageable);
        return pageCountries.map(this::countryToDto);
    }

    private CountryDto countryToDto(Country country) {
        return new CountryDto(
                country.getId(),
                country.getCountryName());
    }
}
//    Page<CountryDto> pageCountryDtos = pageCountries.map(this::countryEntityToDto);
//    List<CountryDto> countryDtos = pageCountryDtos.getContent();