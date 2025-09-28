package movieticketbookingservice.paymentservice.enums;

import lombok.ToString;

@ToString
public enum BookingStatus {
    PENDING,
    CONFIRMED,
    CANCELLED,
    REFUND_INITIATED,
    REFUND_CANCELLED
}
