package com.natwest.services.primenumber.service;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Slf4j
public class PrimeNumberService {

    public List<Integer> getPrimerNumbers(Integer inputNumber){
        log.info("Input Primer Number in service :{}", inputNumber);

        List<Integer> primeNumber = new ArrayList<>();
        IntStream.rangeClosed(0,inputNumber)
                .filter(PrimeNumberService::isPrime)
                .forEach(i -> {
                    log.info("Prime Number: "+ i);
                    primeNumber.add(i);
                });

        return primeNumber;
    }

    private static boolean isPrime(Integer number){
        return number > 1 && LongStream.rangeClosed(2, number/2).noneMatch(l -> number % l ==0);
    }
}
