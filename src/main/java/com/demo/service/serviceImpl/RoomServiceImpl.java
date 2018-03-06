package com.demo.service.serviceImpl;

import com.demo.dao.RoomRepository;
import com.demo.domain.Category;
import com.demo.domain.income.BookRequest;
import com.demo.domain.income.PeriodBookRequest;
import com.demo.domain.outcome.BookResponse;
import com.demo.model.Room;
import com.demo.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final RoomRepository roomRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    @Transactional
    public List<Room> getAllAvailableRoom(PeriodBookRequest periodBookRequest) {
        LOGGER.info("get all available room on date: {}", periodBookRequest);
        Timestamp startDate = periodBookRequest.getStartDate();
        Timestamp endDate = periodBookRequest.getEndDate();
        List<Room> bookedRoomsOnPeriod = roomRepository.findAllByStartBookingDateAndEndBookingDate(startDate, endDate);
        if (bookedRoomsOnPeriod != null){
            LOGGER.info("rooms on period: {} have already booked", periodBookRequest);
        }
        return bookedRoomsOnPeriod;
    }

    @Override
    @Transactional
    public List<Room> getRoomsOfCategory(Category nameCategory) {
        LOGGER.info("get room of category: {}", nameCategory);
        return roomRepository.findAllByCategory(nameCategory);

    }

    @Override
    @Transactional
    public BookResponse bookRoom(BookRequest bookRequest) {
        LOGGER.info("user with id {} books room: {}", bookRequest.getUserId(), bookRequest);

        //TODO

        return new BookResponse(1L, "single", bookRequest.getStartDate(), bookRequest.getEndDate());
    }
}
