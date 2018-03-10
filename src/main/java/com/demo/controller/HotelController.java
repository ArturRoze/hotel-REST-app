package com.demo.controller;

import com.demo.model.Booking;
import com.demo.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.demo.model.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * Controller for {@link Hotel}
 *
 * @author Artyr
 */
@RestController
@RequestMapping("/hotel")
public class HotelController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final HotelService hotelService;

    @Autowired
    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("/{idHotel}/bookings/all")
    public List<Booking> getAllBookingsByHotelId(@PathVariable Long idHotel) {
        LOGGER.info("get all booking rooms in hotel with id: {}", idHotel);
        if (StringUtils.hasLength(idHotel.toString())) {
            return hotelService.getAllBookingsOfHotel(idHotel);
        }
        return null;
    }
}
