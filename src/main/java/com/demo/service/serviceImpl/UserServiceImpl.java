package com.demo.service.serviceImpl;

import com.demo.dao.BookingRepository;
import com.demo.dao.RoomRepository;
import com.demo.dao.UserRepository;
import com.demo.domain.income.BookRequest;
import com.demo.domain.income.UserDataRequest;
import com.demo.model.AdditionalOption;
import com.demo.model.Booking;
import com.demo.model.Room;
import com.demo.model.User;
import com.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;

    private final BookingRepository bookingRepository;

    private final RoomRepository roomRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
        this.roomRepository = roomRepository;
    }

    //WORKs
    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    //WORKs
    @Override
    @Transactional
    public User create(UserDataRequest userDataRequest) {
        LOGGER.info("Save user: {}", userDataRequest);
        User existingUser = userRepository.getByLogin(userDataRequest.getLogin());
        if (existingUser != null) {
            return existingUser;
        }
        User user = new User(userDataRequest.getLogin(), userDataRequest.getName(), userDataRequest.getSurname());

        return userRepository.save(user);
    }

    //WORKs
    @Override
    @Transactional
    public List<Booking> readAllBookingOfUser(Long userId) {
        LOGGER.info("Booking list of user with id: {}", userId);
        List<Booking> allBookingsByUserId = bookingRepository.findAllByUserId(userId);
        if (allBookingsByUserId == null) {
            LOGGER.info("user with id: {} is not exist", userId);
        }
        return allBookingsByUserId;
    }

    //WORKs
    @Override
    @Transactional
    public Double getTotalPriceOfBookings(Long id) {
        List<Booking> allBookingsByUserId = bookingRepository.findAllByUserId(id);
        Double totalPriceBookingsByUserId = 0.0;
        for (Booking booking : allBookingsByUserId) {
            totalPriceBookingsByUserId += booking.getTotalPrice();
        }
        return totalPriceBookingsByUserId;
    }

    //WORKs
    @Override
    @Transactional
    public Double getTotalPriceOfBooking(Long userId, Long bookingId) {
        return bookingRepository.getTotalPriceBookingByUserId(userId, bookingId);
    }
}
