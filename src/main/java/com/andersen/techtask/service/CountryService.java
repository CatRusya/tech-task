package com.andersen.techtask.service;

import com.andersen.techtask.dto.CountryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CountryService {
    Page<CountryDto> getAllCountries(Pageable pageable);


}
