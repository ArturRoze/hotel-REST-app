package com.demo.service.serviceImpl;

import com.demo.dao.BookingRepository;
import com.demo.dao.RoomRepository;
import com.demo.dao.UserRepository;
import com.demo.domain.income.UserDataRequest;
import com.demo.model.Booking;
import com.demo.model.User;
import com.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service for managing {@link User} in repository
 *
 * @author Artur
 * @see UserService
 */
@Service
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;

    private final BookingRepository bookingRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BookingRepository bookingRepository, RoomRepository roomRepository) {
        this.userRepository = userRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<User> getAll() {
        LOGGER.info("getting all user from repository");
        return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional
    public User create(UserDataRequest userDataRequest) {
        LOGGER.info("Save user: {}", userDataRequest);
        User existingUser = userRepository.getByLogin(userDataRequest.getLogin());
        if (existingUser != null) {
            LOGGER.info("user with the same login: {} already exists");
            return existingUser;
        }
        User user = new User(userDataRequest.getLogin(), userDataRequest.getName(), userDataRequest.getSurname());

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public List<Booking> readAllBookingOfUser(Long userId) {
        LOGGER.info("Booking list of user with id: {}", userId);
        return bookingRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public Double getTotalPriceOfBookings(Long userId) {
        LOGGER.info("Get total price of bookings for user with id {}", userId);
        List<Booking> allBookingsByUserId = bookingRepository.findAllByUserId(userId);
        Double totalPriceBookingsByUserId = 0.0;
        for (Booking booking : allBookingsByUserId) {
            totalPriceBookingsByUserId += booking.getTotalPrice();
        }
        return totalPriceBookingsByUserId;
    }

    @Override
    @Transactional
    public Double getTotalPriceBookingByUserId(Long userId, Long bookingId) {
        LOGGER.info("Get total price of booking with id: {} for user with id: {}", userId, bookingId);
        return bookingRepository.getTotalPriceBookingByUserId(userId, bookingId);
    }
}
