package com.natwest.services.primenumber.controller;

import com.natwest.services.primenumber.dto.PrimeNumberResponse;
import com.natwest.services.primenumber.service.PrimeNumberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @RequestMapping(value = "/{number}", method = RequestMethod.GET, produces = "application/json")
    public PrimeNumberResponse getPrimeNumbers(@PathVariable(value = "number") Integer primeNumber){
        log.info("Prime Number input controller: {}",primeNumber);


        return PrimeNumberResponse.builder()
                .initial(primeNumber)
                .primes(primeNumberService.getPrimerNumbers(primeNumber))
                .build();
    }

}
