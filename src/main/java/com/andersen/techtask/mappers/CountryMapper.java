package com.andersen.techtask.mappers;

import com.andersen.techtask.dto.CountryDto;
import com.andersen.techtask.entity.Country;
import org.mapstruct.Mapper;


@Mapper(componentModel ="spring")
public interface CountryMapper {
    CountryDto toDto(Country country);
}
