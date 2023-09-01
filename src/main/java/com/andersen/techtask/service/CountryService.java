package com.andersen.techtask.service;

import com.andersen.techtask.dto.CountryDto;
import com.andersen.techtask.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

public interface CountryService {
  Page<CountryDto> getAllCountries(Pageable pageable);

  Country getCountryById(Long id);

  CountryDto loadLogo(MultipartFile file, Long id);
}
