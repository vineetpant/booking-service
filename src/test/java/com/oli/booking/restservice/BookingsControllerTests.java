package com.oli.booking.restservice;

import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oli.booking.models.Booking;
import com.oli.booking.models.enums.Currency;
import com.oli.booking.models.enums.Department;

@WebMvcTest(BookingsController.class)
public class BookingsControllerTests {
  @Autowired
  private MockMvc mvc;

  @Test
  void testCreateBooking() throws Exception {
    mvc.perform(MockMvcRequestBuilders
        .post("/bookingservice/booking")
        .content(asJsonString(new Booking("Cool description!", (float) 50.0, Currency.USD,
            new Date(683124845000L), "valid@email.ok", Department.ADMIN)))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void testGetBookingById() {

  }

  @Test
  void testGetBookingHash() {

  }

  @Test
  void testGetBookingIdsByDepartment() {

  }

  @Test
  void testGetBookingsCurrencyList() {

  }

  @Test
  void testGetDoBussiness() {

  }

  @Test
  void testGetSumOfBookingsByCurrency() {

  }

  @Test
  void testUpdateBooking() {

  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
