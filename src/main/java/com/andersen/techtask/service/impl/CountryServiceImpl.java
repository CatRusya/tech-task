package com.andersen.techtask.service.impl;

import com.andersen.techtask.dto.CountryDto;
import com.andersen.techtask.entity.Country;
import com.andersen.techtask.repository.CountryRepository;
import com.andersen.techtask.service.CountryService;
import com.andersen.techtask.service.ImageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;



@Service
@RequiredArgsConstructor

public class CountryServiceImpl implements CountryService {

  private final CountryRepository countryRepository;
  private final ImageService imageService;



  @Override
  @Transactional(readOnly = true)
  public Page<CountryDto> getAllCountries(Pageable pageable) {
    Page<Country> pageCountries = countryRepository.findAll(pageable);
    return pageCountries.map(this::countryToDto);
  }

  @Override
  @Transactional(readOnly = true)
  @Cacheable(value = "CountryService::getById", key = "#id")
  public Country getCountryById(Long id) {
    return countryRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Country not found"));
  }

  @Override
  @Transactional
  public CountryDto loadLogo(MultipartFile file, Long id) {
    Country country = getCountryById(id);
    String fullFilePath = imageService.loadLogoFile(file);
    country.setLogo(fullFilePath);
    countryRepository.save(country);
    return new CountryDto(country.getId(), country.getCountryName(), country.getLogo());
  }

  private CountryDto countryToDto(Country country) {
    return new CountryDto(
        country.getId(),
        country.getCountryName(),
        imageService.getUrl(country.getLogo()));
  }


}
