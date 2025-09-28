package com.movie.ticket.booking.service.commons.enums;

import lombok.ToString;

@ToString
public enum BookingStatus {
    PENDING,
    CONFIRMED,
    CANCELLED,
    REFUND_INITIATED,
    REFUND_CANCELLED
}
