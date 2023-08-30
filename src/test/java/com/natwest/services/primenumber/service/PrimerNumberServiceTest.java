package com.natwest.services.primenumber.service;

import com.natwest.services.primenumber.dto.PrimeNumberRequest;
import com.natwest.services.primenumber.exception.InvalidPrimeNumberException;
import com.natwest.services.primenumber.validator.PrimeNumberValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PrimerNumberServiceTest {

    private PrimeNumberService primeNumberService;

    public PrimerNumberServiceTest() {
    }

    @BeforeAll
    public void setup(){
        PrimeNumberValidator primeNumberValidator = new PrimeNumberValidator();
        primeNumberService = new PrimeNumberService(primeNumberValidator);
    }

    @Test
    public void testPrimeNumber() throws InvalidPrimeNumberException {
        //given
        PrimeNumberRequest primeNumberRequest = PrimeNumberRequest.builder()
                .number(10)
                .build();

        //when
        List<Integer> result = primeNumberService.getPrimerNumbers(primeNumberRequest);
        //then
        assertNotNull(result);
        assertFalse(result.isEmpty());

        List<Integer> expected = Arrays.asList(2, 3, 5, 7);

        assertTrue(expected.containsAll(result));
    }

    @Test
    public void testInvalidNumber(){
        //given
        PrimeNumberRequest primeNumberRequest = PrimeNumberRequest.builder()
                .number(-5)
                .build();

        //when

        InvalidPrimeNumberException invalidPrimeNumberException = assertThrows(InvalidPrimeNumberException.class, () ->
                primeNumberService.getPrimerNumbers(primeNumberRequest)
        );
        //then
        assertEquals("Invalid input number", invalidPrimeNumberException.getMessage());

    }
}
