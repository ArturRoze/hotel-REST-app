package com.demo.service;

import com.demo.model.Booking;

import java.util.List;

public interface HotelService {

    List<Booking> getAllBookingsOfHotel(Long id);
}
