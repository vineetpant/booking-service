package com.oli.booking.restservice;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.oli.booking.models.Booking;
import com.oli.booking.models.enums.Currency;
import com.oli.booking.models.enums.Department;
import com.oli.booking.models.response.CreatedBooking;
import com.oli.booking.utility.EmailMock;
import com.oli.booking.utility.TransactionMock;

@Service
public class BookingService {
    // Map of bookingId and Booking
    Map<String, Booking> bookingMap = new HashMap<String, Booking>();
    // Map of transactionId, CreatedBooking
    Map<String, CreatedBooking> transactionMap = new HashMap<String, CreatedBooking>();

    public CreatedBooking createOrUpdate(Booking booking) throws NoSuchAlgorithmException {
        bookingMap.put(booking.getBookingId(), booking);
        CreatedBooking createdBooking = TransactionMock.saveBookingOnGoerli(booking);
        transactionMap.put(createdBooking.transactionId(), createdBooking);
        EmailMock.sendEmail(booking, createdBooking);
        return createdBooking;
    }

    public Booking getBooking(String bookingId) {
        return bookingMap.get(bookingId);
    }

    public List<String> getBookingIdsByDepartment(Department department) {
        List<String> bookingIds = new ArrayList<String>();
        Collection<Booking> bookings = bookingMap.values();
        for (Booking booking : bookings) {
            if (booking.getDepartment() == department) {
                bookingIds.add(booking.getBookingId());
            }
        }
        return bookingIds;
    }

    public List<Currency> getBookingCurrencyList() {
        List<Currency> currencies = new ArrayList<Currency>();
        Collection<Booking> bookings= bookingMap.values();
        for (Booking booking : bookings) {
            if (!currencies.contains(booking.getCurrency())) {
                currencies.add(booking.getCurrency());
            }
        }
        return currencies;
    }

    public String getBookingHash(String transactionId) throws Exception {
        CreatedBooking createdBooking = transactionMap.get(transactionId);
        if (createdBooking.hash().equals(TransactionMock.getBookingHash(bookingMap.get(createdBooking.bookingId())))) {
            return createdBooking.hash();
        } else {
            throw new Exception("Hash doesn't match with original booking");
        }
    }

    public String doBussiness(String bookingId) {
        Booking booking = bookingMap.get(bookingId);
        return booking.getDepartment().doBussiness(booking);
    }

    public float getSumOfBookingsByCurrency(Currency currency) {
        Collection<Booking> bookings =  bookingMap.values();
        float sum = (float) 0.0;
        for (Booking booking : bookings) {
            float conversionRate = (float) 0.0;
            switch (booking.getCurrency()) {
                case EURO:
                    conversionRate = Currency.getEuroConversionRate(currency);
                    break;
                case INR:
                    conversionRate = Currency.getINRConversionRate(currency);
                    break;
                case USD:
                    conversionRate = Currency.getUSDConversionRate(currency);
                    break;

            }
            sum = sum + booking.getPrice() * conversionRate;
        }
        return sum;
    }
}