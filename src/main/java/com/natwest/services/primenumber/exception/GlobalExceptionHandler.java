package com.natwest.services.primenumber.exception;

import com.natwest.services.primenumber.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
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

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException exception,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.info("handleHttpMessageNotReadable bad request: {}", exception.getMessage(), exception);

        ErrorDto errorDto = ErrorDto.builder()
                .message(exception.getMessage())
                .status(status.value())
                .timeStamp(new Date())
                .build();
        return status(status).body(errorDto);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        log.info("handleMethodArgumentNotValid bad request: {}", exception.getMessage(), exception);

        ErrorDto errorDto = ErrorDto.builder()
                .message(exception.getMessage())
                .status(status.value())
                .timeStamp(new Date())
                .build();
        return status(status).body(errorDto);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException exception, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        log.info("handleTypeMismatch bad request: {}", exception.getMessage(), exception);

        ErrorDto errorDto = ErrorDto.builder()
                .message("Invalid request parameter " + exception.getPropertyName() + " - " + exception.getValue())
                .status(status.value())
                .timeStamp(new Date())
                .build();
        return status(status).body(errorDto);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception exception, WebRequest request) {
        log.info("handleAll Exception: {}", exception.getMessage(), exception);
        ErrorDto errorDto = ErrorDto.builder()
                .message(exception.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .timeStamp(new Date())
                .build();
        return status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDto);
    }
}
