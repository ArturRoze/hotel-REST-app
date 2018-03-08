package com.demo.dao;

import com.demo.model.Hotel;
import org.springframework.data.repository.CrudRepository;

/**
 * Specifies methods used to obtain and modify hotel related information
 * which is stored in the database.
 * @author Artur
 */
public interface HotelRepository extends CrudRepository<Hotel, Long> {

}
