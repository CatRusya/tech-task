package com.andersen.techtask.dto.response;

import com.andersen.techtask.dto.CityDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityResponse {

    private Pageable page;

     private List<CityDto> cities;
}
