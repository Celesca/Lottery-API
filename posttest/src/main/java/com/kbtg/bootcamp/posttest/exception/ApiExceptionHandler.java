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

//    @ExceptionHandler(value = {NotFoundException.class})
//    // Exception ถ้าเป็นคลาสนี้เเราจะใช้ Function แล้วรับเข้ามา แล้ว Build Response Error รํูปแบบใหม่
//    public ResponseEntity<Object> handleNotFoundException(NotFoundException notFoundException) {
//
//        ApiExceptionResponse apiExceptionResponse = new ApiExceptionResponse(
//                notFoundException.getMessage(),
//                HttpStatus.NOT_FOUND,
//                ZonedDateTime.now()
//        );
//
//        return new ResponseEntity<>(apiExceptionResponse, HttpStatus.BAD_REQUEST);
//    }




}
