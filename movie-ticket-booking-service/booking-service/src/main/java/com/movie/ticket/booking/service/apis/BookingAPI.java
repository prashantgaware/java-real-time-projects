package com.movie.ticket.booking.service.apis;

import com.movie.ticket.booking.service.commons.dtos.BookingDTO;
import com.movie.ticket.booking.service.commons.dtos.ResponseDTO;
import com.movie.ticket.booking.service.services.BookingService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@Slf4j
public class BookingAPI {

    private final BookingService bookingService;

    public BookingAPI(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/get")
    public ResponseEntity<List<String>> getBookings() {
        log.info("getBookings called");
        List<String> bookings = bookingService.getBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> create(@Valid @RequestBody BookingDTO bookingDto) {
        log.info("create : {}", bookingDto.toString());
        // String bookingInfo = bookingService.create(bookingDto);
        ResponseDTO responseDTO = bookingService.create(bookingDto);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }
}
