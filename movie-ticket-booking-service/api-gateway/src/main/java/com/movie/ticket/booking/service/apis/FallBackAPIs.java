package com.movie.ticket.booking.service.apis;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackAPIs {

    @GetMapping("/bookingServiceFallBack")
    public String bookingServiceFallBack() {
        return "Booking Service is taking longer than expected. Please try again later.";
    }

    @GetMapping("/paymentServiceFallBack")
    public String paymentServiceFallBack() {
        return "Payment Service is taking longer than expected. Please try again later.";
    }
}
