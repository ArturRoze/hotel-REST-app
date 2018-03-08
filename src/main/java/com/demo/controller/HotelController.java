package com.demo.controller;

import com.demo.domain.income.PeriodBookRequest;
import com.demo.model.Booking;
import com.demo.model.Room;
import com.demo.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    //WORKs
    @GetMapping("/{idHotel}/bookings/all")
    public List<Booking> getAllBookingsByHotelId(@PathVariable Long idHotel) {
        LOGGER.info("get all booking rooms in hotel with id: {}", idHotel);
        if (StringUtils.hasLength(idHotel.toString())) {
            return hotelService.getAllBookingsOfHotel(idHotel);
        }
        return null;
    }
}
