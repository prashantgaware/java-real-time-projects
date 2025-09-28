package movieticketbookingservice.paymentservice.apis;

import com.movie.ticket.booking.service.commons.dtos.BookingDTO;
import com.movie.ticket.booking.service.commons.dtos.ResponseDTO;
import movieticketbookingservice.paymentservice.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentHandler {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public ResponseDTO create(@RequestBody BookingDTO bookingDTO) {
        return paymentService.makePayment(bookingDTO);
    }
}
