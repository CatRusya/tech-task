package com.andersen.techtask.dto.response;

import com.andersen.techtask.dto.CityDto;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityResponse {
  @JsonIgnore
  private Pageable page;
  private List<CityDto> cities;
}
