package movieticketbookingservice.paymentservice.enums;

import lombok.ToString;

@ToString
public enum PaymentStatus {
    PENDING,
    APPROVED,
    FAILED,
    CANCELLED
}
