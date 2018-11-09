package com.demo.controller;

import com.demo.domain.income.UserDataRequest;
import com.demo.domain.outcome.UserDataResponse;
import com.demo.model.Booking;
import com.demo.model.User;
import com.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for {@link User}
 *
 * @author Artyr
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public UserDataResponse createUser(@RequestBody UserDataRequest request) {
        LOGGER.info("Creating User : {}", request);
        if (StringUtils.hasLength(request.getName()) && StringUtils.hasLength(request.getSurname()) && StringUtils.hasLength(request.getLogin())) {
            User user = userService.create(request);
            UserDataResponse userDataResponse = new UserDataResponse();
            userDataResponse.setUserId(user.getId());
            userDataResponse.setLogin(user.getLogin());
            userDataResponse.setName(user.getName());
            userDataResponse.setSurname(user.getSurname());
            return userDataResponse;
        } else {
            return null;
        }
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        LOGGER.info("get all users");
        List<User> allUsers = userService.getAll();
        if (allUsers != null) {
            return allUsers;
        }
        return null;
    }

    @GetMapping("{userId}/booking")
    public List<Booking> getBooking(@PathVariable Long userId) {
        LOGGER.info("get booking by user with id: {}", userId);
        if (userId != null) {
            return userService.readAllBookingOfUser(userId);
        }
        return null;
    }

    @GetMapping("{userId}/bookings/total_price")
    public Double getTotalPriceOfBookings(@PathVariable Long userId) {
        LOGGER.info("get total price of bookings by user with id: {}", userId);
        if (userId != null) {
            return userService.getTotalPriceOfBookings(userId);
        }
        return null;
    }

    @GetMapping("{userId}/booking/{bookingId}/total_price")
    public Double getTotalPriceBookingByUserId(@PathVariable Long userId, @PathVariable Long bookingId) {
        LOGGER.info("get total price of booking by user with id: {} and booking id: {}", userId, bookingId);
        if (userId != null && bookingId != null) {
            return userService.getTotalPriceBookingByUserId(userId, bookingId);
        }
        return null;
    }
}
