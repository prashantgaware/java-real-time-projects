package movieticketbookingservice.paymentservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResultDTO {
    private boolean success;       // did payment succeed?
    private String paymentId;      // ID from Dodo
    private String status;         // e.g. "succeeded", "pending", "failed"
    private String message;
}
