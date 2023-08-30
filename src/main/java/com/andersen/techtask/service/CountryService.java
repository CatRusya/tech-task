package com.andersen.techtask.service;

import com.andersen.techtask.dto.CountryDto;
import com.andersen.techtask.entity.Country;
import com.andersen.techtask.service.impl.CountryServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CountryService {
    Page<CountryDto> getAllCountries(Pageable pageable);

    Country getCountryById(Long id);

    CountryDto loadLogo(MultipartFile file, Long id) throws Exception;
}
