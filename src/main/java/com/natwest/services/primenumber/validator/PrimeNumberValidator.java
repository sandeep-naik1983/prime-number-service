package com.natwest.services.primenumber.validator;

import com.natwest.services.primenumber.dto.PrimeNumberRequest;

import static org.springframework.util.ObjectUtils.isEmpty;

public class PrimeNumberValidator {
    public Boolean validate(PrimeNumberRequest primeNumberRequest) {

        return !isEmpty(primeNumberRequest) && !isEmpty(primeNumberRequest.getNumber()) && primeNumberRequest.getNumber() > 1;
    }
}
