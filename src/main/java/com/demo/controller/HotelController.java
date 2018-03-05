package com.demo.controller;

import com.demo.domain.income.PeriodBookRequest;
import com.demo.model.Room;
import com.demo.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping("/rooms/allBooking")
    public List<Room> getAllBookingRoomsOfHotel(@RequestBody PeriodBookRequest request) {
        LOGGER.info("get all booking rooms in hotel on date: {}", request);
        if (StringUtils.hasLength(request.toString())) {
            return hotelService.getAllBookingRoomsOfHotel(request);
        }
        return null;
    }
}
