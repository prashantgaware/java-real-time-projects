package com.movie.ticket.booking.service.services.impl;

import com.movie.ticket.booking.service.broker.PaymentServiceBroker;
import com.movie.ticket.booking.service.commons.dtos.BookingDTO;
import com.movie.ticket.booking.service.commons.dtos.ResponseDTO;
import com.movie.ticket.booking.service.commons.enums.BookingStatus;
import com.movie.ticket.booking.service.entities.BookingEntity;
import com.movie.ticket.booking.service.repositories.BookingRepository;
import com.movie.ticket.booking.service.services.BookingService;
import jakarta.transaction.Transactional;
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
    @Transactional
    public ResponseDTO create(BookingDTO bookingDto) {
        log.info("create : {}", bookingDto.toString());
        BookingEntity bookingEntity = BookingEntity.builder()
                .movieId(bookingDto.getMovieId())
                .bookingAmount(bookingDto.getBookingAmount())
                .bookingStatus(BookingStatus.PENDING)
                .seatsSelected(bookingDto.getSeatsSelected())
                .showDate(bookingDto.getShowDate())
                .showTime(bookingDto.getShowTime())
                .userId(bookingDto.getUserId())
                .build();
        this.bookingRepository.save(bookingEntity);
        ResponseDTO responseDTO = paymentServiceBroker.create(
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

        if (responseDTO.getBookingDetails().getBookingStatus().equals(BookingStatus.CONFIRMED)) {
            bookingEntity.setBookingStatus(BookingStatus.CONFIRMED);
            this.bookingRepository.save(bookingEntity);
        } else {
            bookingEntity.setBookingStatus(BookingStatus.CANCELLED);
            this.bookingRepository.save(bookingEntity);
        }

        //return "Booking created successfully with booking id : " + save.getBookingId();
        return ResponseDTO.builder()
                .bookingDetails(
                        BookingDTO.builder()
                                .bookingId(responseDTO.getBookingDetails().getBookingId())
                                .bookingAmount(responseDTO.getBookingDetails().getBookingAmount())
                                .bookingStatus(responseDTO.getBookingDetails().getBookingStatus())
                                .seatsSelected(responseDTO.getBookingDetails().getSeatsSelected())
                                .showDate(responseDTO.getBookingDetails().getShowDate())
                                .showTime(responseDTO.getBookingDetails().getShowTime())
                                .userId(responseDTO.getBookingDetails().getUserId())
                                .movieId(responseDTO.getBookingDetails().getMovieId())
                                .build()
                )
                .build();
    }
}
