package movieticketbookingservice.paymentservice.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import movieticketbookingservice.paymentservice.dtos.BookingDTO;
import movieticketbookingservice.paymentservice.enums.BookingStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class StripePaymentGateway {

    @Value(value = "${stripe.key}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public BookingDTO makePayment(BookingDTO bookingDTO) {
        Map<String ,Object> map = new HashMap<>();
        map.put("amount", (int)Math.round(bookingDTO.getBookingAmount() * 100));
        map.put("currency", "inr");
        map.put("description", "Test Payment");
        map.put("source", "tok_visa");
        try {
            Charge.create(map);
            bookingDTO.setBookingStatus(BookingStatus.CONFIRMED);
        } catch (StripeException e) {
            log.error(e.getMessage());
            bookingDTO.setBookingStatus(BookingStatus.CANCELLED);
        }
        return bookingDTO;
    }
}
