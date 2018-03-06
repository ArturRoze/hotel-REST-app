package com.demo.service;

import com.demo.domain.Category;
import com.demo.domain.income.BookRequest;
import com.demo.domain.income.PeriodBookRequest;
import com.demo.domain.outcome.BookResponse;
import com.demo.model.Room;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public interface RoomService {

    List<Room> getAllAvailableRoom(PeriodBookRequest periodBookRequest);

    List<Room> getRoomsOfCategory(Category nameCategory);

    BookResponse bookRoom(BookRequest bookRequest);

}
