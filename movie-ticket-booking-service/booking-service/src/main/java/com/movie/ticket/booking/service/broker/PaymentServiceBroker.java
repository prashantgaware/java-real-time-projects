package com.movie.ticket.booking.service.broker;

import com.movie.ticket.booking.service.commons.dtos.BookingDTO;
import com.movie.ticket.booking.service.commons.dtos.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "payment-service", url = "http://localhost:2021")
public interface PaymentServiceBroker {

    @PostMapping("/payments/create")
    ResponseDTO create(BookingDTO bookingDTO);

}
