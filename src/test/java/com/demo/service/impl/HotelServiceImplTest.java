package com.demo.service.impl;

import com.demo.dao.BookingRepository;
import com.demo.model.Booking;
import com.demo.service.serviceImpl.HotelServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class HotelServiceImplTest {

    @BeforeClass
    public static void beforeClass() {
        initMocks(HotelServiceImpl.class);
    }

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private HotelServiceImpl hotelService;

    @Test
    public void getAllBookingsOfHotelTest() {

        //arrange
        Booking booking = new Booking();
        booking.setId(435L);
        List<Booking> expectedBookings = Collections.singletonList(booking);

        when(bookingRepository.getAllBookingsByHotelId(123L)).thenReturn(expectedBookings);

        //action
        List<Booking> actual = hotelService.getAllBookingsOfHotel(123L);

        //assert
        assertEquals(expectedBookings, actual);
        verify(bookingRepository).getAllBookingsByHotelId(123L);
    }
}
