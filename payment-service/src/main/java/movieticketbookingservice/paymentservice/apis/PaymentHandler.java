package movieticketbookingservice.paymentservice.apis;

import movieticketbookingservice.paymentservice.dtos.BookingDTO;
import movieticketbookingservice.paymentservice.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public BookingDTO create(@RequestBody BookingDTO bookingDTO) {
        return paymentService.makePayment(bookingDTO);
    }
}
