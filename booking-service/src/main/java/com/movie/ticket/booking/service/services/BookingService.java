package com.movie.ticket.booking.service.services;

import com.movie.ticket.booking.service.dtos.BookingDTO;

public interface BookingService {
    String create(BookingDTO bookingDto);
}
