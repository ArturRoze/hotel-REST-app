package com.demo.service;

import com.demo.domain.income.PeriodBookRequest;
import com.demo.model.Room;

import java.util.List;

public interface HotelService {

    List<Room> getAllBookingRoomsOfHotel(PeriodBookRequest period);
}