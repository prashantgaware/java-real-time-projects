package com.movie.ticket.booking.service.services.impl;

import com.movie.ticket.booking.service.broker.PaymentServiceBroker;
import com.movie.ticket.booking.service.dtos.BookingDTO;
import com.movie.ticket.booking.service.dtos.ResponseDTO;
import com.movie.ticket.booking.service.entities.BookingEntity;
import com.movie.ticket.booking.service.enums.BookingStatus;
import com.movie.ticket.booking.service.repositories.BookingRepository;
import com.movie.ticket.booking.service.services.BookingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BookingServiceImpl implements BookingService {


    @Autowired
    private PaymentServiceBroker paymentServiceBroker;

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public ResponseDTO create(BookingDTO bookingDto) {
        log.info("create : {}", bookingDto.toString());
        BookingEntity bookingEntity = this.bookingRepository.save(
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

        BookingDTO bookingDTO = paymentServiceBroker.create(
                BookingDTO.builder()
                        .bookingId(bookingEntity.getBookingId())
                        .userId(bookingEntity.getUserId())
                        .movieId(bookingEntity.getMovieId())
                        .seatsSelected(bookingEntity.getSeatsSelected())
                        .showDate(bookingEntity.getShowDate())
                        .showTime(bookingEntity.getShowTime())
                        .bookingAmount(bookingEntity.getBookingAmount())
                        .build()
        );

        //return "Booking created successfully with booking id : " + save.getBookingId();
        return ResponseDTO.builder()
                .bookingDetails(
                        BookingDTO.builder()
                                .bookingId(bookingDTO.getBookingId())
                                .bookingAmount(bookingDTO.getBookingAmount())
                                .bookingStatus(bookingDTO.getBookingStatus())
                                .seatsSelected(bookingDTO.getSeatsSelected())
                                .showDate(bookingDTO.getShowDate())
                                .showTime(bookingDTO.getShowTime())
                                .userId(bookingDTO.getUserId())
                                .movieId(bookingDTO.getMovieId())
                                .build()
                )
                .build();
    }
}
