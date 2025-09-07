package com.movie.ticket.booking.service.services;

import com.movie.ticket.booking.service.dtos.BookingDTO;
import com.movie.ticket.booking.service.dtos.ResponseDTO;

public interface BookingService {
    ResponseDTO create(BookingDTO bookingDto);
}
