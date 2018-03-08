package com.demo.dao;

import com.demo.model.Booking;
import com.demo.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * Specifies methods used to obtain and modify booking related information
 * which is stored in the database.
 * @author Artur
 */
public interface BookingRepository extends CrudRepository<Booking, Long> {

    List<Booking> findAllByUserId(Long id);

    //WORKs
    @Query ("select b.totalPrice from Booking b where b.userId = :userId AND b.id = :bookingId")
    Double getTotalPriceBookingByUserId(@Param("userId")Long userId, @Param("bookingId")Long bookingId);

    Booking findByUserId(Long id);

    @Query("select roomId from Booking")
    List<Long> getRoomIds(List<Booking> bookings);

    //WORKs
    @Query("select b from Booking b where b.roomId in (select r.id from Room r where r.hotelId = :id)")
    List<Booking> getAllBookingsByHotelId(@Param("id") Long id);

}
