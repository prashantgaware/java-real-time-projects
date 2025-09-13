package movieticketbookingservice.paymentservice.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public enum PaymentStatus {
    PENDING,
    APPROVED,
    FAILED,
    CANCELLED
}
