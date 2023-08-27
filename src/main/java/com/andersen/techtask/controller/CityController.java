package com.andersen.techtask.controller;

import com.andersen.techtask.dto.CityDto;


import com.andersen.techtask.service.CityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/cities")
@RequiredArgsConstructor
@Validated
@Tag(name = "City Controller", description = "City API")
public class CityController {

//    private final CityService cityService;
//
//    @Operation(summary = "Returns paginated list of all cities by country")
//    @GetMapping("/country")
//    public ResponseEntity<Page<CityDto>> getAllCitiesByCountry(
//            @RequestParam(value = "page", defaultValue = "0") int page,
//            @RequestParam(value = "per_page", defaultValue = "25") int size,
//            @RequestParam(required = false) Long id) {
//
//        var pageable = PageRequest.of(page, size);
//        return ResponseEntity.ok(
//                cityService.getAllCitiesByCountry(id,pageable)
//        );
//
//    }



}
