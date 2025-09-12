package com.movie.ticket.booking.service.broker;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "payment-service", url = "http://localhost:2021")
public interface PaymentBroker {

    @PostMapping("/payments/create")
    public String create();

}
