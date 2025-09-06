package com.movie.ticket.booking.service.handlers;

import com.movie.ticket.booking.service.dtos.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
@Slf4j
public class BookingAPIHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.info("Entered into BookingAPIHandler methodArgumentNotValidException with the exception: {}", e.getMessage());
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        ResponseDTO  responseDTO = ResponseDTO.builder()
                .errorMessage(allErrors.getFirst().getDefaultMessage())
                .build();
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
