package com.demo.controller;

import com.demo.service.HotelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;


public class HotelControllerTest {

    @Autowired
    private HotelService hotelService;

    @Test
    public void getAllBookingsByHotelIdTest() {
    }
}
