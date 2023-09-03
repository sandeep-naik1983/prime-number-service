package com.natwest.services.primenumber.service;

import com.natwest.services.primenumber.constant.AlgorithmType;
import com.natwest.services.primenumber.dto.PrimeNumberRequest;
import com.natwest.services.primenumber.exception.InvalidPrimeNumberException;
import com.natwest.services.primenumber.validator.PrimeNumberValidator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@AllArgsConstructor
@Slf4j
public class PrimeNumberService {

    private PrimeNumberValidator primeNumberValidator;

    private static boolean isPrimeNumberComplexAlgorithm(Integer number) {
        return number > 1 && LongStream.rangeClosed(2, number / 2).noneMatch(l -> number % l == 0);
    }

    private static boolean isPrimeNumberSimpleAlgorithm(Integer number) {
        int remainder;

        if (number <= 1) return false;

        for (int i = 2; i <= number / 2; i++) {
            remainder = number % i;
            if (remainder == 0) { //if remainder is 0 then number is not prime number and breaks loop
                return false;
            }
        }
        return true;
    }

    @Cacheable("myCache")
    public List<Integer> getPrimerNumbers(PrimeNumberRequest primeNumberRequest) throws InvalidPrimeNumberException {
        //Validate input
        Boolean isValidNumber = primeNumberValidator.validate(primeNumberRequest);

        if (!isValidNumber) {
            log.error("Invalid input number :{}", primeNumberRequest.getNumber());
            throw new InvalidPrimeNumberException("Invalid input number");
        }
        log.info("Input Primer Number in service :{}", primeNumberRequest.getNumber());

        AlgorithmType algorithmType;

        if (!ObjectUtils.isEmpty(primeNumberRequest.getAlgorithmType())) {
            algorithmType = primeNumberRequest.getAlgorithmType();
        } else {
            algorithmType = AlgorithmType.SIMPLE;
        }

        List<Integer> primeNumber = new ArrayList<>();
        IntStream.rangeClosed(0, primeNumberRequest.getNumber())
                .filter(value -> AlgorithmType.SIMPLE.equals(algorithmType) ?
                        isPrimeNumberSimpleAlgorithm(value) : isPrimeNumberComplexAlgorithm(value))
                .forEach(i -> {
                    log.info("Prime Number: " + i);
                    primeNumber.add(i);
                });

        return primeNumber;
    }
}
