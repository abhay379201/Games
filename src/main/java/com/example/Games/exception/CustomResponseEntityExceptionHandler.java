package com.example.Games.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//@RestController
@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        GenericRequestParameterErrorResponse exceptionResponse = new GenericRequestParameterErrorResponse();
        exceptionResponse.paramName = request.toString();
        exceptionResponse.description = ex.getMessage();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        GenericRequestParameterErrorResponse response = new GenericRequestParameterErrorResponse();
        response.paramName = ex.getParameterName();
        response.description = ex.getMessage();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
//        System.out.println("itsme");
        return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<Object> handleMethodArhumentTypeMisamtch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        GenericRequestParameterErrorResponse response = new GenericRequestParameterErrorResponse();
        response.paramName = ex.getName();
        response.description = ex.getMessage();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}