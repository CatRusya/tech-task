package com.andersen.techtask.controller;

import com.andersen.techtask.AbstractTest;
import com.andersen.techtask.controller.data.TestDataBuilder;
import com.andersen.techtask.dto.CountryDto;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CountryControllerTest extends AbstractTest {

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
    void shouldReturnCountriesPerPageIfJwtIsValid() throws Exception {

        String contentAsString = this.mockMvc.perform(get("/api/v1/countries?page=0&per_page=15")
                        .header(tokenHeaderName,
                                tokenPrefix + testDataBuilder.getValidTestAccessToken("johndoe@gmail.com")))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode jsonNode = objectMapper.readTree(contentAsString);
        JsonNode contentNode = jsonNode.get("content");
        List<CountryDto> countries = null;
        if (contentNode.isArray()) {
             countries = objectMapper.readValue(
                    contentNode.toString(),
                    new TypeReference<>() {}
            );
        }
        assertEquals(testDataBuilder.getAllCountries(), countries);
    }

    @Test
    void failsIfNoAuthorizationHeaderInRequestReturnsUnauthorized() throws Exception {
        this.mockMvc.perform(get("/api/v1/countries?page=0&per_page=15"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Sql(value = "/db.dml/schema.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/db.dml/fill-schema.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/db.dml/drop-table.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void failsIfJwtInRequestHeaderIsExpiredReturnsUnauthorized() throws Exception {
        this.mockMvc.perform(get("/api/v1/countries")
                        .header(tokenHeaderName, tokenPrefix
                                + testDataBuilder.getExpiredTestAccessToken("johndoe@gmail.com")))
                .andExpect(status().isUnauthorized());
    }
}
