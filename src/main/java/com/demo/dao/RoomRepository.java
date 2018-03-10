package com.demo.dao;

import com.demo.domain.Category;
import com.demo.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * Specifies methods used to obtain and modify room related information
 * which is stored in the database.
 *
 * @author Artur
 */
public interface RoomRepository extends CrudRepository<Room, Long> {

    //WORKs
    List<Room> findAllByCategory(Category category);

    //WORKs
    @Query("select r from Room r where r.id not in (select b.roomId from Booking b where not (b.startDate <= :startDate or b.endDate >= :endDate))")
    List<Room> getAllAvailableRoomsOnPeriod(@Param("startDate") Timestamp starDate, @Param("endDate") Timestamp endDate);
}
