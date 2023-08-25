package com.oli.booking.models;

import java.util.Date;
import java.util.UUID;

import com.oli.booking.models.enums.Currency;
import com.oli.booking.models.enums.Department;

public class Booking {
    String bookingId;
    String description;
    Float price;
    Currency currency;
    Date subscriptionStartDate;
    String email;
    Department department;

    public Booking(
            String description,
            Float price,
            Currency currency,
            Date subscriptionStartDate,
            String email,
            Department department) {
        this.bookingId = UUID.randomUUID().toString();
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.subscriptionStartDate = subscriptionStartDate;
        this.email = email;
        this.department = department;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Date getSubscriptionStartDate() {
        return subscriptionStartDate;
    }

    public void setSubscriptionStartDate(Date subscriptionStartDate) {
        this.subscriptionStartDate = subscriptionStartDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

}
