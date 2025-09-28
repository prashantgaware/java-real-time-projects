package movieticketbookingservice.paymentservice.services;


import com.movie.ticket.booking.service.commons.dtos.BookingDTO;
import com.movie.ticket.booking.service.commons.dtos.ResponseDTO;

public interface PaymentService {
    ResponseDTO makePayment(BookingDTO bookingDTO);
}
