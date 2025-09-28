package com.movie.ticket.booking.service.handlers;

import com.movie.ticket.booking.service.commons.handlers.GlobalExceptionHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class BookingAPIHandler extends GlobalExceptionHandler {
    // to handle booking service specific exceptions if any
}
