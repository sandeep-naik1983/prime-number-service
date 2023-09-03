package com.natwest.services.primenumber.controller;

import com.natwest.services.primenumber.constant.AlgorithmType;
import com.natwest.services.primenumber.dto.ErrorDto;
import com.natwest.services.primenumber.dto.PrimeNumberResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PrimeNumberIntegrationController {

    @LocalServerPort
    private int port;

    private String v1GETEndpoint;

    @Autowired
    TestRestTemplate testRestTemplate;

    @BeforeAll
    void setup(){
        v1GETEndpoint = "http://localhost:" +port+"/prime-number-service/v1/primes/";
        //http://localhost:8080/prime-number-service/v1/primes/10?
    }

    @Test
    public void getValidPrimeNumberTest_Default_Algorithm() { //SIMPLE
        //given
        int input = 10;
        //when
        ResponseEntity<PrimeNumberResponse> responseResponseEntity = testRestTemplate.getForEntity(v1GETEndpoint+input,
                PrimeNumberResponse.class);
        //then
        assertEquals(HttpStatus.OK, responseResponseEntity.getStatusCode());

        PrimeNumberResponse primeNumberResponse = responseResponseEntity.getBody();
        assertNotNull(primeNumberResponse);

        List<Integer> result = primeNumberResponse.getPrimes();
        assertNotNull(result);
        assertFalse(result.isEmpty());

        List<Integer> expected = Arrays.asList(2, 3, 5, 7);

        assertTrue(expected.containsAll(result));
    }

    @Test
    public void getInvalidPrimeNumberTest_Default_Algorithm() { //SIMPLE
        //given
        int input = -5;
        //when
        ResponseEntity<ErrorDto> responseResponseEntity = testRestTemplate.getForEntity(v1GETEndpoint+input,
                ErrorDto.class);
        //then
        ErrorDto errorDto = responseResponseEntity.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, responseResponseEntity.getStatusCode());

        assertNotNull(errorDto);
        assertEquals("Invalid input number", errorDto.getMessage());
    }

    @Test
    public void getValidPrimeNumberTest_COMPLEX_Algorithm() {
        //given
        int input = 10;
        AlgorithmType algorithmType = AlgorithmType.COMPLEX;
        //when
        ResponseEntity<PrimeNumberResponse> responseResponseEntity =
                testRestTemplate.getForEntity(v1GETEndpoint + input + "?algorithm=" + algorithmType.name(),
                        PrimeNumberResponse.class);
        //then
        assertEquals(HttpStatus.OK, responseResponseEntity.getStatusCode());

        PrimeNumberResponse primeNumberResponse = responseResponseEntity.getBody();
        assertNotNull(primeNumberResponse);

        List<Integer> result = primeNumberResponse.getPrimes();
        assertNotNull(result);
        assertFalse(result.isEmpty());

        List<Integer> expected = Arrays.asList(2, 3, 5, 7);

        assertTrue(expected.containsAll(result));
    }

    @Test
    public void getPrimeNumberTest_Invalid_Algorithm() {
        //given
        int input = -5;
        //when
        String algorithmType = "Unknown";
        //when
        ResponseEntity<ErrorDto> responseResponseEntity = testRestTemplate.getForEntity(v1GETEndpoint + input + "?algorithm=" + algorithmType,
                ErrorDto.class);
        //then
        ErrorDto errorDto = responseResponseEntity.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, responseResponseEntity.getStatusCode());

        assertNotNull(errorDto);
        assertEquals("Invalid request parameter algorithm - " + algorithmType, errorDto.getMessage());
    }

    @Test
    public void getInvalidPrimeNumberTest_COMPLEX_Algorithm() {
        //given
        int input = -5;
        //when
        AlgorithmType algorithmType = AlgorithmType.COMPLEX;
        //when
        ResponseEntity<ErrorDto> responseResponseEntity = testRestTemplate.getForEntity(v1GETEndpoint + input + "?algorithm=" + algorithmType.name(),
                ErrorDto.class);
        //then
        ErrorDto errorDto = responseResponseEntity.getBody();

        assertEquals(HttpStatus.BAD_REQUEST, responseResponseEntity.getStatusCode());

        assertNotNull(errorDto);
        assertEquals("Invalid input number", errorDto.getMessage());
    }
}
