package com.movie.ticket.booking.service.apis;

import com.movie.ticket.booking.service.dtos.BookingDTO;
import com.movie.ticket.booking.service.services.BookingService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bookings")
@Slf4j
public class BookingAPI {

    @Autowired
    private BookingService bookingService;

    @PostMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody BookingDTO bookingDto) {
        log.info("create : {}", bookingDto.toString());
        String bookingInfo = bookingService.create(bookingDto);
        return new ResponseEntity<>(bookingInfo, HttpStatus.OK);
    }
}
