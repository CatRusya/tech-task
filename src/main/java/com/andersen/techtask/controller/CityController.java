package com.andersen.techtask.controller;

import com.andersen.techtask.dto.CityDto;
import com.andersen.techtask.dto.response.CityResponse;
import com.andersen.techtask.exception.InvalidInputException;
import com.andersen.techtask.service.CityService;
import com.andersen.techtask.validation.OnUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/cities")
@RequiredArgsConstructor
@Validated
@Tag(name = "City Controller", description = "City API")
public class CityController {

  private final CityService cityService;


  @GetMapping
  @Operation(summary = "Return paginated list of all cities by country")
  public ResponseEntity<CityResponse> getAllCities(
      @RequestParam(value = "page") int page,
      @RequestParam(value = "per_page") int size,
      @RequestParam(required = false) String cityName,
      @RequestParam(required = false) String countryName) {
    log.info("Log in method 'getAllCities' in city service");
    if (countryName != null && cityName != null) {
      throw new InvalidInputException();
    }

    return ResponseEntity.ok(
        cityService.getAllCities(page, size, cityName, countryName));

  }


  @PatchMapping("/{id}")
  @Operation(summary = "Edit city name")
  @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
  public ResponseEntity<CityDto> editCity(@Valid @PositiveOrZero @PathVariable Long id,
      @Validated(OnUpdate.class) @RequestBody CityDto cityDto) {
    log.info("Log in method 'editCity' in city service");
    return ResponseEntity.ok(cityService.editCityName(id, cityDto));
  }



}
