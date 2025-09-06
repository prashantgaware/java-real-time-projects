package com.movie.ticket.booking.service.services.impl;

import com.movie.ticket.booking.service.dtos.BookingDTO;
import com.movie.ticket.booking.service.dtos.ResponseDTO;
import com.movie.ticket.booking.service.entities.BookingEntity;
import com.movie.ticket.booking.service.enums.BookingStatus;
import com.movie.ticket.booking.service.repositories.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookingServiceImpl implements com.movie.ticket.booking.service.services.BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Override
    public String create(BookingDTO bookingDto) {
        log.info("create : {}", bookingDto.toString());
        BookingEntity save = bookingRepository.save(
                BookingEntity.builder()
                        .movieId(bookingDto.getMovieId())
                        .bookingAmount(bookingDto.getBookingAmount())
                        .bookingStatus(BookingStatus.PENDING)
                        .seatsSelected(bookingDto.getSeatsSelected())
                        .showDate(bookingDto.getShowDate())
                        .showTime(bookingDto.getShowTime())
                        .userId(bookingDto.getUserId())
                        .build()
        );

        return "Booking created successfully with booking id : " + save.getBookingId();
    }
}
