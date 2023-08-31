package com.natwest.services.primenumber.service;

import com.natwest.services.primenumber.dto.PrimeNumberRequest;
import com.natwest.services.primenumber.exception.InvalidPrimeNumberException;
import com.natwest.services.primenumber.validator.PrimeNumberValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@AllArgsConstructor
@Slf4j
public class PrimeNumberService {

    private PrimeNumberValidator primeNumberValidator;

    @Cacheable("myCache")
    public List<Integer> getPrimerNumbers(PrimeNumberRequest primeNumberRequest) throws InvalidPrimeNumberException {
        //Validate input
        Boolean isValidNumber = primeNumberValidator.validate(primeNumberRequest);

        if(!isValidNumber){
            log.error("Invalid input number :{}", primeNumberRequest.getNumber());
            throw new InvalidPrimeNumberException("Invalid input number");
        }
        log.info("Input Primer Number in service :{}", primeNumberRequest.getNumber());

        List<Integer> primeNumber = new ArrayList<>();
        IntStream.rangeClosed(0,primeNumberRequest.getNumber())
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
