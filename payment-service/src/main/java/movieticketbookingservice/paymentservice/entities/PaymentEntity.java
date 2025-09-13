package movieticketbookingservice.paymentservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import movieticketbookingservice.paymentservice.enums.BookingStatus;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "payment_table")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID paymentId;
    private UUID bookingId;
    private BookingStatus bookingStatus;
    private Double paymentAmount;
    private LocalDateTime paymentDateTime;
}
