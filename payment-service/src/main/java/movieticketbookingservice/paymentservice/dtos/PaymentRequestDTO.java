package movieticketbookingservice.paymentservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequestDTO {
    private UUID bookingId;
    private Double amount;
    private String currency;
    private String description;
}
