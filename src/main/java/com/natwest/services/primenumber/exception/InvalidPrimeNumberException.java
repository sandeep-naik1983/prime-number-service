package com.natwest.services.primenumber.exception;

public class InvalidPrimeNumberException extends Exception {
    public InvalidPrimeNumberException(String errorMessage) {
        super(errorMessage);
    }

    public InvalidPrimeNumberException(String errorMessage, Throwable throwable) {
        super(errorMessage, throwable);
    }
}
