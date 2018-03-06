package com.demo.dao;

import com.demo.model.Booking;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Long> {

//    List<Booking> findAllByUserId(Long id);
//
//    @Override
//    Iterable<Booking> findAll();
}
