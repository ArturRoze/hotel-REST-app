package com.demo.service.serviceImpl;

import com.demo.dao.BookingRepository;
import com.demo.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl implements BookingService{

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final BookingRepository bookingRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository hotelRepository) {
        this.bookingRepository = hotelRepository;
    }
}
