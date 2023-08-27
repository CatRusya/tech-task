package com.andersen.techtask.dto;

import com.andersen.techtask.entity.City;
import com.andersen.techtask.validation.OnCreate;
import com.andersen.techtask.validation.OnUpdate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Country DTO")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CountryDto {

    @Schema(description = "Country 1", example = "1")
    @NotNull(message = "Id must be not null", groups = OnUpdate.class)
    Long id;

    @Schema(description = "Country name", example = "Turkey")
    @NotNull(message = "Country name must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Country name must be smaller than 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    String name;

}
