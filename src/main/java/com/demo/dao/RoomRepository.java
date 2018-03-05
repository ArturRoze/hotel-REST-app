package com.demo.dao;

import com.demo.domain.Category;
import com.demo.model.Room;
import org.springframework.data.repository.CrudRepository;

import java.sql.Timestamp;
import java.util.List;

public interface RoomRepository extends CrudRepository<Room, Long> {

    List<Room> findAllByCategory(Category category);

    List<Room> findAllByStartBookingDateAndEndBookingDate(Timestamp startBookingDate, Timestamp endBookingDate);

    List<Room> findAllByUserId(Long id);
}
