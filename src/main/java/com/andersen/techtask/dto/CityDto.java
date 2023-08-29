package com.andersen.techtask.dto;

import com.andersen.techtask.entity.Country;
import com.andersen.techtask.validation.OnCreate;
import com.andersen.techtask.validation.OnUpdate;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "City DTO")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CityDto {
    @NotNull (message = "Id must be not null", groups = OnUpdate.class)
    Long id;

    @NotNull(message = "City name must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "City name must be smaller than 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    String cityName;

    @NotNull(message = "Country name must be not null", groups = {OnCreate.class, OnUpdate.class})
    @Length(max = 255, message = "Country name must be smaller than 255 symbols", groups = {OnCreate.class, OnUpdate.class})
    String countryName;

    @Schema(example = "uuid-logo-name-with-extension.gif")
    @Length(max = 255)
    String logo;
}
