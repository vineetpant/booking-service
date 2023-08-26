package com.oli.booking.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CreatedBooking(
        @JsonProperty("booking_id") String bookingId,
        @JsonProperty("transaction_id") String transactionId,
        String hash) {
}
