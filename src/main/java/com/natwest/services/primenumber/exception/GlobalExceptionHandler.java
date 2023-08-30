package com.natwest.services.primenumber.exception;

import com.natwest.services.primenumber.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.ResponseEntity.status;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidPrimeNumberException.class)
    public ResponseEntity<Object> handleBadRequest(InvalidPrimeNumberException exception){
        log.info("Handling bad request: {}", exception.getMessage(), exception);
        ErrorDto errorDto = ErrorDto.builder()
                .message(exception.getMessage())
                .status(BAD_REQUEST.value())
                .timeStamp(new Date())
                .build();
        return status(BAD_REQUEST).body(errorDto);
    }
}
