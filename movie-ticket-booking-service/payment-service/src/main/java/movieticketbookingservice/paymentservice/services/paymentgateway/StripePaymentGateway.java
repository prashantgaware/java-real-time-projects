package movieticketbookingservice.paymentservice.services.paymentgateway;

import com.movie.ticket.booking.service.commons.dtos.BookingDTO;
import com.movie.ticket.booking.service.commons.enums.BookingStatus;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@Primary
public class StripePaymentGateway implements PaymentGateway {

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
