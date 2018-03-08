package com.demo.controller;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/all")
    public List<Room> getAllAvailableRooms(@RequestBody PeriodBookRequest periodBookRequest) {
        LOGGER.info("get all available rooms on date: {}", periodBookRequest);
        if (periodBookRequest != null){
            return roomService.getAllAvailableRoom(periodBookRequest);
        }
        return null;
    }
    //WORKs
    @GetMapping("/category")
    public List<Room> getRoomsOfCategory(@RequestParam Category nameCategory) {
        LOGGER.info("get rooms with category: {}", nameCategory);
        if (nameCategory != null) {
            return roomService.getRoomsOfCategory(nameCategory);
        }
        return null;
    }

    @PostMapping("/booking")
    public BookResponse bookRoomOnDate(@RequestParam BookRequest request) {
        LOGGER.info("user books room on date: {}", request);
        if (request != null){

            //TODO

        }
        return null;
    }
}
