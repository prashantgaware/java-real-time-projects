package com.movie.ticket.booking.service.services.impl;

import com.movie.ticket.booking.service.dtos.BookingDTO;
import com.movie.ticket.booking.service.dtos.ResponseDTO;
import com.movie.ticket.booking.service.entities.BookingEntity;
import com.movie.ticket.booking.service.enums.BookingStatus;
import com.movie.ticket.booking.service.repositories.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookingServiceImpl implements com.movie.ticket.booking.service.services.BookingService {

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public ResponseDTO create(BookingDTO bookingDto) {
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

        //return "Booking created successfully with booking id : " + save.getBookingId();
        return ResponseDTO.builder()
                .bookingDetails(
                        BookingDTO.builder()
                                .bookingId(save.getBookingId())
                                .bookingAmount(save.getBookingAmount())
                                .bookingStatus(BookingStatus.CONFIRMED)
                                .seatsSelected(save.getSeatsSelected())
                                .showDate(save.getShowDate())
                                .showTime(save.getShowTime())
                                .userId(save.getUserId())
                                .movieId(save.getMovieId())
                                .build()
                )
                .build();
    }
}
