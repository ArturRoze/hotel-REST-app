package com.demo.controller;

import com.demo.domain.Category;
import com.demo.domain.income.BookRequest;
import com.demo.model.Room;
import com.demo.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/all/{date}")
    public List<Room> getAllAvailableRooms(@RequestParam("date") Timestamp date) {
        LOGGER.info("get all available rooms on date: {}", date);
        List<Room> availableRoomsOnDate = new ArrayList<>();


        return availableRoomsOnDate;
    }

    @GetMapping("/category/{nameCategory}")
    public List<Room> getRoomsOfCategory(@RequestParam Category nameCategory) {
        LOGGER.info("get rooms with category: {}", nameCategory);
        if (nameCategory != null) {
            return roomService.getRoomsOfCategory(nameCategory);
        }
        return null;
    }

    @PostMapping("/booking")
    public ResponseEntity<String> bookRoomOnDate(@RequestBody BookRequest request) {
        LOGGER.info("user books room with category on date: {}", request);


        return new ResponseEntity<>(HttpStatus.OK);
    }
}
