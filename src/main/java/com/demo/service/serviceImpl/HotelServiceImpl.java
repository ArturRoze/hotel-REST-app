package com.demo.service.serviceImpl;

import com.demo.dao.BookingRepository;
import com.demo.model.Booking;
import com.demo.model.Hotel;
import com.demo.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for managing {@link Hotel} in repository
 *
 * @author Artur
 * @see HotelService
 */
@Service
public class HotelServiceImpl implements HotelService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final BookingRepository bookingRepository;

    @Autowired
    public HotelServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public List<Booking> getAllBookingsOfHotel(Long idHotel) {
        LOGGER.info("getting all bookings from hotel with id: {}", idHotel);
        return bookingRepository.getAllBookingsByHotelId(idHotel);
    }
}
