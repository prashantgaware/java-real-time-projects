package movieticketbookingservice.paymentservice.services.impl;

import com.movie.ticket.booking.service.commons.dtos.BookingDTO;
import com.movie.ticket.booking.service.commons.dtos.ResponseDTO;
import com.movie.ticket.booking.service.commons.enums.BookingStatus;
import com.stripe.exception.StripeException;
import jakarta.transaction.Transactional;
import movieticketbookingservice.paymentservice.entities.PaymentEntity;
import movieticketbookingservice.paymentservice.repositories.PaymentRepository;
import movieticketbookingservice.paymentservice.services.PaymentService;

import movieticketbookingservice.paymentservice.services.paymentgateway.StripePaymentGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    /*@Autowired
    private DodoPaymentService dodoPaymentService;*/

    @Autowired
    private StripePaymentGateway stripePaymentGateway;

    /*@Override
    @Transactional
    public BookingDTO makePayment(BookingDTO bookingDTO) {
        // 1. Call external payment
        PaymentResultDTO payResult = dodoPaymentService.processPayment(bookingDTO);

        // 2. Save to DB
        PaymentEntity p = PaymentEntity.builder()
                .bookingId(bookingDTO.getBookingId())
                .paymentAmount(bookingDTO.getBookingAmount())
                .paymentDateTime(LocalDateTime.now())
                .build();

        // set booking/payment status
        if (payResult.isSuccess()) {
            p.setBookingStatus(BookingStatus.CONFIRMED);
        } else {
            p.setBookingStatus(BookingStatus.CANCELLED);
        }

        paymentRepository.save(p);

        // 3. Return BookingDTO updated
        return BookingDTO.builder()
                .bookingId(bookingDTO.getBookingId())
                .userId(bookingDTO.getUserId())
                .movieId(bookingDTO.getMovieId())
                .seatsSelected(bookingDTO.getSeatsSelected())
                .showDate(bookingDTO.getShowDate())
                .showTime(bookingDTO.getShowTime())
                .bookingAmount(bookingDTO.getBookingAmount())
                .bookingStatus(p.getBookingStatus())
                .build();
    }*/

    @Override
    @Transactional(rollbackOn = StripeException.class)
    public ResponseDTO makePayment(BookingDTO bookingDTO) {
        bookingDTO = this.stripePaymentGateway.makePayment(bookingDTO);

        PaymentEntity paymentEntity = PaymentEntity.builder()
                .bookingId(bookingDTO.getBookingId())
                .bookingStatus(bookingDTO.getBookingStatus())
                .paymentAmount(bookingDTO.getBookingAmount())
                .build();
        this.paymentRepository.save(paymentEntity);
        if (bookingDTO.getBookingStatus().equals(BookingStatus.CONFIRMED)) {
            paymentEntity.setBookingStatus(BookingStatus.CONFIRMED);
            paymentEntity.setPaymentDateTime(LocalDateTime.now());
        } else {
            paymentEntity.setBookingStatus(BookingStatus.CANCELLED);
            paymentEntity.setPaymentDateTime(LocalDateTime.now());
        }

        return ResponseDTO.builder()
                .bookingDetails(bookingDTO)
                .build();
    }
}
