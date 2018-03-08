package com.demo.service.serviceImpl;

import com.demo.dao.BookingRepository;
import com.demo.dao.HotelRepository;
import com.demo.dao.RoomRepository;
import com.demo.domain.income.PeriodBookRequest;
import com.demo.model.Booking;
import com.demo.model.Room;
import com.demo.service.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final BookingRepository bookingRepository;

    private final RoomRepository roomRepository;

    @Autowired
    public HotelServiceImpl(BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }


    public List<Booking> getAllBookingsOfHotel(Long idHotel){
        return bookingRepository.getAllBookingsByHotelId(idHotel);
    }
}
