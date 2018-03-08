package com.demo.service.serviceImpl;

import com.demo.dao.BookingRepository;
import com.demo.dao.RoomRepository;
import com.demo.domain.Category;
import com.demo.domain.income.BookRequest;
import com.demo.domain.income.PeriodBookRequest;
import com.demo.domain.outcome.BookResponse;
import com.demo.model.Booking;
import com.demo.model.Room;
import com.demo.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final RoomRepository roomRepository;

    private final BookingRepository bookingRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    //WORKs //TODO
    @Override
    @Transactional
    public List<Room> getAllAvailableRoom(PeriodBookRequest periodBookRequest) {
        LOGGER.info("get all available room on date: {} ", periodBookRequest);
        List<Long> allAvailableIdRoomsOnPeriod = roomRepository.getAllAvailableRoomsOnPeriod(periodBookRequest.getStartDate(), periodBookRequest.getEndDate());
        return (List<Room>) roomRepository.findAll(allAvailableIdRoomsOnPeriod);
    }

    //WORKs
    @Override
    @Transactional
    public List<Room> getRoomsOfCategory(Category nameCategory) {
        LOGGER.info("get room of category: {}", nameCategory);
        return roomRepository.findAllByCategory(nameCategory);

    }

    @Override
    @Transactional
    public BookResponse bookRoom(BookRequest bookRequest) {
        LOGGER.info("user with id {} books room: {}", bookRequest.getUserId(), bookRequest.getRoomId());
        Long userId = bookRequest.getUserId();
        Room room = roomRepository.findOne(userId);

        // TODO
        room.getPrice();
        new Booking();

        return new BookResponse(1L, 2L, 350L, bookRequest.getStartDate(), bookRequest.getEndDate());
    }
}
