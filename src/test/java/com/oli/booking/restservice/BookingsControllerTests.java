package com.oli.booking.restservice;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oli.booking.models.Booking;
import com.oli.booking.models.enums.Currency;
import com.oli.booking.models.enums.Department;
import com.oli.booking.models.response.CreatedBooking;

@WebMvcTest(BookingsController.class)
public class BookingsControllerTests {
  @Autowired
  private MockMvc mvc;
  private Booking booking;
  private CreatedBooking createdBooking;

  @MockBean
  private BookingService bookingService;

  @BeforeEach
  void init() {
    booking = new Booking("Cool description!", (float) 50.0, Currency.USD,
        new Date(683124845000L), "valid@email.ok", Department.ADMIN);

    createdBooking = new CreatedBooking(booking.getBookingId(), "transactionId", "hash");
  }

  @Test
  void testCreateBooking() throws Exception {
    mvc.perform(MockMvcRequestBuilders
        .post("/bookingservice/bookings")
        .content(asJsonString(booking))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andDo(print());
  }

  @Test
  void testGetBookingById() throws Exception {
    	when(bookingService.getBooking(anyString())).thenReturn(booking);
      mvc.perform(get("/bookingservice/bookings/{bookingId}", booking.getBookingId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.email", is(booking.getEmail())));
  }

  @Test
  void testGetBookingHash() throws Exception {
      when(bookingService.getBookingHash(anyString())).thenReturn(createdBooking.hash());
      mvc.perform(get("/bookingservice/bookings/proof/{transactionId}", createdBooking.transactionId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", is(createdBooking.hash())));
  }

  @Test
  void testGetBookingIdsByDepartment() throws Exception {
    List<String> list = new ArrayList<>();
    list.add("booking1");
    list.add("booking2");
    when(bookingService.getBookingIdsByDepartment(any())).thenReturn(list);
    mvc.perform(get("/bookingservice/bookings/department/{department}", booking.getDepartment()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", is(list)));
  }

  @Test
  void testGetBookingsCurrencyList() throws Exception {
    List<Currency> list = new ArrayList<>();
    list.add(Currency.EURO);
    list.add(Currency.USD);
    when(bookingService.getBookingCurrencyList()).thenReturn(list);
    mvc.perform(get("/bookingservice/bookings/currencies"))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  void testGetDoBussiness() throws Exception {
    when(bookingService.doBussiness(anyString())).thenReturn(Department.ADMIN.doBussiness(booking));
    mvc.perform(get("/bookingservice/bookings/dobusiness/{bookingId}", booking.getBookingId()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", is(Department.ADMIN.doBussiness(booking))));
  }

  @Test
  void testGetSumOfBookingsByCurrency() throws Exception {

   when(bookingService.getSumOfBookingsByCurrency(any())).thenReturn(booking.getPrice() * Currency.getUSDConversionRate(Currency.INR));
    mvc.perform(get("/bookingservice/sum/{currency}", Currency.INR))
        .andDo(print())
        .andExpect(status().isOk());
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
