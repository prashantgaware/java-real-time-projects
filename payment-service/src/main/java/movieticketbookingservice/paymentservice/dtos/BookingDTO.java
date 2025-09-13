package movieticketbookingservice.paymentservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import movieticketbookingservice.paymentservice.enums.BookingStatus;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class BookingDTO {
    private UUID bookingId;

    private String userId;

    private Integer movieId;

    private List<String> seatsSelected;

    private LocalDate showDate;

    private LocalTime showTime;

    private Double bookingAmount;

    private BookingStatus bookingStatus;
}
