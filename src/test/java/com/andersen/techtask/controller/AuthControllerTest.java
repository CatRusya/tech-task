package com.andersen.techtask.controller;

import com.andersen.techtask.AbstractTest;
import com.andersen.techtask.dto.auth.JwtRequest;
import com.andersen.techtask.dto.auth.JwtResponse;
import com.andersen.techtask.entity.Role;
import com.andersen.techtask.entity.User;
import com.andersen.techtask.security.JwtTokenProvider;
import com.andersen.techtask.service.UserService;
import com.andersen.techtask.service.props.JwtProperties;
import com.fasterxml.jackson.databind.JsonNode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureWebTestClient
@RequiredArgsConstructor
public class AuthControllerTest extends AbstractTest {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Test
    @Sql(value = "/db.dml/schema.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/db.dml/fill-schema.sql",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/db.dml/drop-table.sql",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void successfulAuthenticateShouldReturnEmailAndWellFormedAccessAndRefreshTokens()
            throws Exception {
        JwtRequest jwtRequest = JwtRequest.builder()
                .username("johndoe@gmail.com")
                .password("12345")
                .build();

        String contentAsString = this.mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(jwtRequest)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode responseJson = objectMapper.readTree(contentAsString);

        assertEquals("johndoe@gmail.com", responseJson.get("username").asText());
        assertTrue(jwtTokenProvider.validateToken(responseJson.get("accessToken").asText()));
        assertTrue(jwtTokenProvider.validateToken(responseJson.get("refreshToken").asText()));
    }
}
