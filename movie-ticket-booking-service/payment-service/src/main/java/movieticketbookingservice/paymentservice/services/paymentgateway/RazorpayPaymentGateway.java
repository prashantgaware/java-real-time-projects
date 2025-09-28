package movieticketbookingservice.paymentservice.services.paymentgateway;

import com.movie.ticket.booking.service.commons.dtos.BookingDTO;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RazorpayPaymentGateway implements PaymentGateway{

    @Value("${razorpay.api.key}")
    private String apiKey;

    @Value("${razorpay.api.secret}")
    private String secretKey;

    @PostConstruct
    public void init() {
        // Initialize Razorpay client with apiKey and secretKey
    }

    @Override
    public BookingDTO makePayment(BookingDTO bookingDTO) {
        return null;
    }
}
