package com.demo.service.impl;

import com.demo.dao.BookingRepository;
import com.demo.model.Booking;
import com.demo.service.serviceImpl.HotelServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HotelServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private HotelServiceImpl hotelService;

    @Test
    public void testGetAllBookingsOfHotelTest() {

        Booking booking = new Booking();
        booking.setId(456L);
        List<Booking> bookings = Arrays.asList(booking);

        when(bookingRepository.getAllBookingsByHotelId(123L)).thenReturn(bookings);

        List<Booking> result = hotelService.getAllBookingsOfHotel(123L);

        Assert.assertEquals(result, bookings);

        verify(bookingRepository).getAllBookingsByHotelId(123L);
    }
}
