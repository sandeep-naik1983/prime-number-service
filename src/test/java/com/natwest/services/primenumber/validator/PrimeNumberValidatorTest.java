package com.natwest.services.primenumber.validator;

import com.natwest.services.primenumber.dto.PrimeNumberRequest;
import com.natwest.services.primenumber.exception.InvalidPrimeNumberException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PrimeNumberValidatorTest {

    private PrimeNumberValidator primeNumberValidator;


    @BeforeAll
    public void setup(){
        primeNumberValidator = new PrimeNumberValidator();
    }

    @Test
    public void testValidPrimeNumber() throws InvalidPrimeNumberException {
        //given
        PrimeNumberRequest primeNumberRequest = PrimeNumberRequest.builder()
                .number(10)
                .build();

        //when
        Boolean result =
                primeNumberValidator.validate(primeNumberRequest);
        //then
        assertTrue(result);
    }

    @Test
    public void testNumber1(){
        //given
        PrimeNumberRequest primeNumberRequest = PrimeNumberRequest.builder()
                .number(1)
                .build();

        //when
        Boolean result =
                primeNumberValidator.validate(primeNumberRequest);
        //then
        assertFalse(result);

    }

    @Test
    public void testInvalidNumber(){
        //given
        PrimeNumberRequest primeNumberRequest = PrimeNumberRequest.builder()
                .number(-5)
                .build();

        //when
        Boolean result =
                primeNumberValidator.validate(primeNumberRequest);
        //then
        assertFalse(result);

    }
}
