package com.andersen.techtask.controller;

import com.andersen.techtask.dto.CountryDto;
import com.andersen.techtask.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/countries")
@Validated
@Tag(name = "Country Controller", description = "Country API")
public class CountryController {

  private final CountryService countryService;

  @GetMapping
  @Operation(summary = "Get the list of all countries")
  public ResponseEntity<Page<CountryDto>> getAllCountries(
      @RequestParam(value = "page") int page,
      @RequestParam(value = "per_page") int size) {
    log.info("Log in method 'getAllCountries' in country service");
    return ResponseEntity.ok(countryService.getAllCountries(PageRequest.of(page, size)));
  }

  @PostMapping("/{id}/upload")
  @Operation(summary = "Upload logo for country")
  @PreAuthorize("@customSecurityExpression.canAccessUser(#id)")
  public ResponseEntity<CountryDto> loadLogo(
      @RequestParam("file") MultipartFile file,
      @PositiveOrZero @PathVariable Long id) throws Exception {
    log.info("Log in method 'loadLogo' in country service");
    return ResponseEntity.ok(countryService.loadLogo(file, id));
  }



}
