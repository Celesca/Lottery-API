package com.kbtg.bootcamp.posttest.exception;

import com.kbtg.bootcamp.posttest.exception.ApiExceptionResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZonedDateTime;

// Helps Controller to catch response/exceptions that happens
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {


    // Bad Request Exception
    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(BadRequestException badRequestException) {
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(
                badRequestException.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiExceptionResponse, HttpStatus.BAD_REQUEST);
    }

    // Internal Service Exception
    @ExceptionHandler(value = {InternalServiceException.class})
    public ResponseEntity<Object> handleInternalServiceException(InternalServiceException internalServiceException) {
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(
                internalServiceException.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Not Found Exception
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException notFoundException) {
        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(
                notFoundException.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(apiExceptionResponse, HttpStatus.NOT_FOUND);
    }








}
