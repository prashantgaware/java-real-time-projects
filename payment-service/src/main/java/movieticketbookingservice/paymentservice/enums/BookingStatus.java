package movieticketbookingservice.paymentservice.enums;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public enum BookingStatus {
    PENDING,
    CONFIRMED,
    CANCELLED,
    REFUND_INITIATED,
    REFUND_CANCELLED
}
