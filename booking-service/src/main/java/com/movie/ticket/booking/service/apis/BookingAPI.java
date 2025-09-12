package com.movie.ticket.booking.service.apis;

import com.movie.ticket.booking.service.broker.PaymentBroker;
import com.movie.ticket.booking.service.dtos.BookingDTO;
import com.movie.ticket.booking.service.dtos.ResponseDTO;
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

    private final BookingService bookingService;

    @Autowired
    private PaymentBroker paymentBroker;

    public BookingAPI(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(@Valid @RequestBody BookingDTO bookingDto) {
        log.info("create : {}", bookingDto.toString());
        // String bookingInfo = bookingService.create(bookingDto);
        String paymentResponse = paymentBroker.create();
        log.info("payment : {}", paymentResponse);
        ResponseDTO responseDTO = bookingService.create(bookingDto);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
