package com.demo.service.serviceImpl;

import com.demo.dao.HotelRepository;
import com.demo.dao.RoomRepository;
import com.demo.domain.income.PeriodBookRequest;
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

    private final HotelRepository hotelRepository;

    private final RoomRepository roomRepository;

    @Autowired
    public HotelServiceImpl(HotelRepository hotelRepository, RoomRepository roomRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    @Transactional
    public List<Room> getAllBookingRoomsOfHotel(PeriodBookRequest period) {
        LOGGER.info("get all rooms of hotel on period: {}", period);
        List<Room> allBookingRooms = roomRepository.findAllByStartBookingDateAndEndBookingDate(period.getStartDate(), period.getEndDate());
        if (allBookingRooms == null){
            LOGGER.info("There are no booked rooms");
        }
        return allBookingRooms;
    }
}
