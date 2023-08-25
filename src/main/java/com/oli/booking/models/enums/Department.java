package com.oli.booking.models.enums;

import com.oli.booking.models.Booking;

public enum Department {
    SALES,
    ADMIN,
    RESEARCH;

    public String doBussiness(Booking booking) {
        return "Your booking order for " + booking.getPrice() + booking.getCurrency() + " received by "
                + this.toString();
    }
}
