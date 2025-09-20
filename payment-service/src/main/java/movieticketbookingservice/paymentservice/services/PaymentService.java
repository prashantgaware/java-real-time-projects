package movieticketbookingservice.paymentservice.services;

import movieticketbookingservice.paymentservice.dtos.BookingDTO;
import movieticketbookingservice.paymentservice.dtos.ResponseDTO;

public interface PaymentService {
    ResponseDTO makePayment(BookingDTO bookingDTO);
}
