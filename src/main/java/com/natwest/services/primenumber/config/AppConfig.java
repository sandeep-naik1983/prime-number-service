package com.natwest.services.primenumber.config;

import com.natwest.services.primenumber.service.PrimeNumberService;
import com.natwest.services.primenumber.validator.PrimeNumberValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public PrimeNumberValidator primeNumberValidator(){
        return new PrimeNumberValidator();
    }

    @Bean
    public PrimeNumberService primeNumberService(PrimeNumberValidator primeNumberValidator){
        return new PrimeNumberService(primeNumberValidator);
    }
}
