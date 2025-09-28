package com.movie.ticket.booking.service.commons.handlers;

import com.movie.ticket.booking.service.commons.dtos.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDTO> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.info("Entered into BookingAPIHandler methodArgumentNotValidException with the exception: {}", e.getMessage());
        return new ResponseEntity<>(ResponseDTO.builder()
                .statusCodeDescription(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .errorMessages(
                        e.getBindingResult().getAllErrors()
                                .stream()
                                .map(ObjectError::getDefaultMessage)
                                .collect(Collectors.toList())
                ).build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ResponseDTO> runtimeExceptionHandler(RuntimeException e) {
        log.info("Entered into BookingAPIHandler runtimeExceptionHandler with the exception: {}", e.getMessage());
        return new ResponseEntity<>(ResponseDTO.builder()
                .statusCodeDescription(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                .errorMessages(
                        e.getMessage() != null ?
                                e.getMessage().lines().collect(Collectors.toList()) :
                                "Something went wrong".lines().collect(Collectors.toList())
                ).build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
