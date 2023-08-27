package com.andersen.techtask.mappers;

import com.andersen.techtask.dto.CityDto;
import com.andersen.techtask.entity.City;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel ="spring")
public interface CityMapper {

    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    CityDto toDto(City city);


}
