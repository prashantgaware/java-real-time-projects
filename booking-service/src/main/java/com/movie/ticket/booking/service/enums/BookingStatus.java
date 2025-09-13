package com.movie.ticket.booking.service.enums;

import lombok.ToString;

@ToString
public enum BookingStatus {
    PENDING,
    CONFIRMED,
    CANCELLED,
    REFUND_INITIATED,
    REFUND_CANCELLED
}
