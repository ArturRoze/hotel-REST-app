package com.demo.service.serviceImpl;

import com.demo.dao.RoomRepository;
import com.demo.domain.Category;
import com.demo.domain.income.BookRequest;
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
    public List<Room> getAllAvailableRoom(Timestamp date) {
        LOGGER.info("get all available room on date: {}", date);


        return null;
    }

    @Override
    @Transactional
    public List<Room> getRoomsOfCategory(Category nameCategory) {
        LOGGER.info("get room of category: {}", nameCategory);
        List<Room> allRoomsByCategory = roomRepository.findAllByCategory(nameCategory);
        if (allRoomsByCategory == null){
            LOGGER.info("room with category: {} is not exist", nameCategory);
        }
        return allRoomsByCategory;
    }

    @Override
    @Transactional
    public void bookRoom(BookRequest bookRequest) {
        LOGGER.info("user with id {} books room: {}", bookRequest.getUserId(), bookRequest);

    }
}
