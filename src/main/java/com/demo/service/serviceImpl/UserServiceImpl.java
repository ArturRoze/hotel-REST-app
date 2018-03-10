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

    /**
     * Method reads users from repository
     *
     * @return list of {@link User} from repository
     */
    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    /**
     * Method saves {@link User} to repository
     *
     * @param userDataRequest {@link UserDataRequest} to save
     */
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

    /**
     * Method collects all booking from repositories
     *
     * @param userId
     * @return list of all {@link Booking} from repositories by id user
     */
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

    /**
     * Method get total price of booking for specific user
     *
     * @param userId id of user
     * @return total price of bookings for specific user
     */
    @Override
    @Transactional
    public Double getTotalPriceOfBookings(Long userId) {
        List<Booking> allBookingsByUserId = bookingRepository.findAllByUserId(userId);
        Double totalPriceBookingsByUserId = 0.0;
        for (Booking booking : allBookingsByUserId) {
            totalPriceBookingsByUserId += booking.getTotalPrice();
        }
        return totalPriceBookingsByUserId;
    }

    /**
     * Method get total price of booking for specific user
     *
     * @param userId id of user
     * @param bookingId id of booking
     * @return total price of booking for user
     */
    @Override
    @Transactional
    public Double getTotalPriceBookingByUserId(Long userId, Long bookingId) {
        return bookingRepository.getTotalPriceBookingByUserId(userId, bookingId);
    }
}
