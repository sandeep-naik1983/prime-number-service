package com.natwest.services.primenumber.controller;

import com.natwest.services.primenumber.dto.PrimeNumberRequest;
import com.natwest.services.primenumber.dto.PrimeNumberResponse;
import com.natwest.services.primenumber.exception.InvalidPrimeNumberException;
import com.natwest.services.primenumber.service.PrimeNumberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/primes")
@Slf4j
@AllArgsConstructor
public class PrimeNumberController {

    private PrimeNumberService primeNumberService;

    @RequestMapping(value = "/{number}", method = RequestMethod.GET
            , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public PrimeNumberResponse getPrimeNumbers(@PathVariable(value = "number") Integer primeNumber) throws InvalidPrimeNumberException {
        log.info("Prime Number input controller: {}", primeNumber);

        //Prepare DTO
        PrimeNumberRequest primeNumberRequest = PrimeNumberRequest.builder()
                .number(primeNumber)
                .build();

        return PrimeNumberResponse.builder()
                .initial(primeNumberRequest.getNumber())
                .primes(primeNumberService.getPrimerNumbers(primeNumberRequest))
                .build();
    }

}
