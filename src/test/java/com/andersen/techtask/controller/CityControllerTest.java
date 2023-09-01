package com.andersen.techtask.controller;

import com.andersen.techtask.AbstractTest;
import com.andersen.techtask.controller.data.TestDataBuilder;
import com.andersen.techtask.dto.CityDto;
import com.andersen.techtask.security.JwtEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CityControllerTest extends AbstractTest {

    @Value("${security.jwt.header}")
    String tokenHeaderName;

    @Value("${security.jwt.token-prefix}")
    String tokenPrefix;

    @Autowired
    private TestDataBuilder testDataBuilder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Sql(value = "/db.dml/schema.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/db.dml/fill-schema.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/db.dml/drop-table.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void shouldReturnAllCityIfJwtIsValid() throws Exception {
        String contentAsString = this.mockMvc.perform(get("/api/v1/cities?page=0&per_page=15")
                        .header(tokenHeaderName,
                                tokenPrefix + testDataBuilder.getValidTestAccessToken("johndoe@gmail.com")))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode rootNode = objectMapper.readTree(contentAsString);
        JsonNode citiesNode = rootNode.get("cities");
        List<CityDto> cities = objectMapper.readValue(citiesNode.toString(), new TypeReference<List<CityDto>>() {});

        assertEquals(testDataBuilder.getAllCities(), cities);
    }

    @Test
    @Sql(value = "/db.dml/schema.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/db.dml/fill-schema.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/db.dml/drop-table.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void shouldReturnCityPerPageIfJwtIsValid() throws Exception {
        String contentAsString = this.mockMvc.perform(get("/api/v1/cities?page=0&per_page=15&cityName=Tokyo")
                        .header(tokenHeaderName,
                                tokenPrefix + testDataBuilder.getValidTestAccessToken("johndoe@gmail.com")))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode rootNode = objectMapper.readTree(contentAsString);
        JsonNode citiesNode = rootNode.get("cities");
        List<CityDto> cities = objectMapper.readValue(citiesNode.toString(), new TypeReference<List<CityDto>>() {});

        assertEquals(testDataBuilder.getCity(), cities);
    }

    @Test
    @Sql(value = "/db.dml/schema.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/db.dml/fill-schema.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/db.dml/drop-table.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void shouldReturnCitiesByCountryPerPageIfJwtIsValid() throws Exception {
        String contentAsString = this.mockMvc.perform(get("/api/v1/cities?page=0&per_page=15&countryName=Japan")
                        .header(tokenHeaderName,
                                tokenPrefix + testDataBuilder.getValidTestAccessToken("johndoe@gmail.com")))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode rootNode = objectMapper.readTree(contentAsString);
        JsonNode citiesNode = rootNode.get("cities");

        List<CityDto> cities = objectMapper.readValue(citiesNode.toString(), new TypeReference<List<CityDto>>() {});

        assertEquals(testDataBuilder.getAllCities(), cities);
    }

}