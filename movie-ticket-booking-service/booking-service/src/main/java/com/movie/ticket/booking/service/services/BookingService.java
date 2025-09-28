package com.movie.ticket.booking.service.services;

import com.movie.ticket.booking.service.commons.dtos.BookingDTO;
import com.movie.ticket.booking.service.commons.dtos.ResponseDTO;

public interface BookingService {
    ResponseDTO create(BookingDTO bookingDto);
}
