package com.natwest.services.primenumber.config;

import com.natwest.services.primenumber.service.PrimeNumberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public PrimeNumberService primeNumberCheckerService(){
        return new PrimeNumberService();
    }

}
