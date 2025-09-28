package com.movie.ticket.booking.service.commons.dtos;

import com.movie.ticket.booking.service.commons.enums.BookingStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

    @NotNull(message = "Please provide user ID.")
    private String userId;

    @NotNull(message = "Movie ID is must.")
    @Positive(message = "Please provide a valid movie ID.")
    private Integer movieId;

    @NotNull(message = "You need to select at least one seat.")
    private List<String> seatsSelected;

    @NotNull(message = "Select the show date.")
    private LocalDate showDate;

    @NotNull(message = "Select the show time.")
    private LocalTime showTime;

    @NotNull(message = "Booking amount is mandatory.")
    @Positive(message = "Booking amount must be positive.")
    private Double bookingAmount;

    private BookingStatus bookingStatus;
}
