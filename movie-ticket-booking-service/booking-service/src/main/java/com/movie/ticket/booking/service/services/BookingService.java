package com.movie.ticket.booking.service.services;

import com.movie.ticket.booking.service.commons.dtos.BookingDTO;
import com.movie.ticket.booking.service.commons.dtos.ResponseDTO;

import java.util.List;

public interface BookingService {
    List<String> getBookings();
    ResponseDTO create(BookingDTO bookingDto);
}
