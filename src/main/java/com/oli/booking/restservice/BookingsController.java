package com.oli.booking.restservice;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oli.booking.models.Booking;
import com.oli.booking.models.enums.Currency;
import com.oli.booking.models.enums.Department;
import com.oli.booking.models.response.CreatedBooking;

@RestController
@RequestMapping(path = "/bookingservice", produces = MediaType.APPLICATION_JSON_VALUE)
public class BookingsController {
    BookingService bookingService = new BookingService();

    @PostMapping(path = "/booking")
    public CreatedBooking createBooking(@RequestBody Booking booking) throws Exception {
        try {
            return bookingService.createOrUpdate(booking);
        } catch (Exception e) {
            throw new Exception("Error Occured " + e.getMessage());
        }
    }

    @PutMapping(path = "/booking/{booking_id}")
    public CreatedBooking updateBooking(
            @PathVariable String bookingId,
            @RequestBody Booking booking) throws Exception {
        booking.setBookingId(bookingId);
        try {
            return bookingService.createOrUpdate(booking);
        } catch (Exception e) {
            throw new Exception("Error Occured " + e.getMessage());
        }
    }

    @GetMapping("/bookings/{booking_id}")
    public Booking getBookingById(@PathVariable String bookingId) {
        return bookingService.getBooking(bookingId);
    }

    @GetMapping("/bookings/department/{department} ")
    public List<String> getBookingIdsByDepartment(@PathVariable Department department) {
        return bookingService.getBookingIdsByDepartment(department);
    }

    @GetMapping("/bookings/currencies")
    public List<Currency> getBookingsCurrencyList() {
        return bookingService.getBookingCurrencyList();
    }

    @GetMapping("/sum/{currency}")
    public float getSumOfBookingsByCurrency(@PathVariable Currency currency) {
        return bookingService.getSumOfBookingsByCurrency(currency);
    }

    @GetMapping("/bookings/proof/{transaction_id}")
    public String getBookingHash(@PathVariable String transactionId) throws Exception {
        return bookingService.getBookingHash(transactionId);
    }

    @GetMapping("/bookings/dobusiness/{booking_id}")
    public String getDoBussiness(@PathVariable String bookingId) {
        return bookingService.doBussiness(bookingId);
    }

}
