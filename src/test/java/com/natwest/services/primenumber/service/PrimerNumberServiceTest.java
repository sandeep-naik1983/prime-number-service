package com.natwest.services.primenumber.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class PrimerNumberServiceTest {

    private final PrimeNumberService primeNumberService = new PrimeNumberService();

    @Test
    public void testPrimeNumber(){
        //given
        Integer number = 10;

        //when
        List<Integer> result = primeNumberService.getPrimerNumbers(number);
        //then
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());

        List<Integer> expected = Arrays.asList(2,3,5,7);
        //result.containsAll(expected);

        Assertions.assertTrue(expected.containsAll(result));
    }
}
