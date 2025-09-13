package movieticketbookingservice.paymentservice.services;

import movieticketbookingservice.paymentservice.dtos.BookingDTO;

public interface PaymentService {
    BookingDTO makePayment(BookingDTO bookingDTO);
}
