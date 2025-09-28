package movieticketbookingservice.paymentservice.services.paymentgateway;

import com.movie.ticket.booking.service.commons.dtos.BookingDTO;

public interface PaymentGateway {
    BookingDTO makePayment(BookingDTO bookingDTO);
}
