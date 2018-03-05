package com.demo.dao;

import com.demo.model.Hotel;
import com.demo.model.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface HotelRepository extends CrudRepository<Hotel, Long> {

}
