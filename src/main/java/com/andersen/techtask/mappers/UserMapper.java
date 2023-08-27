package com.andersen.techtask.mappers;

import com.andersen.techtask.entity.User;
import com.andersen.techtask.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel ="spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
