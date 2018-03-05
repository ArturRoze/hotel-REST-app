package com.demo.controller;

import com.demo.domain.income.UserDataRequest;
import com.demo.domain.outcome.UserDataResponse;
import com.demo.model.Room;
import com.demo.model.User;
import com.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private UserService userService;

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

    @GetMapping("/booking/{id}")
    public List<Room> getBooking(@RequestParam("id") Long userId) {
        LOGGER.info("get booking rooms by user with id: {}", userId);
        if (userId != null) {
            return userService.readAllBookingOfUser(userId);
        }
        return null;
    }

    @GetMapping("/booking/total_price/{id}")
    public Double getTotalPriceOfBookings(@RequestParam("id") Long userId) {
        LOGGER.info("get total price of booking room by user with id: {}", userId);
        if (userId != null) {
            return userService.getTotalPriceOfBooking(userId);
        }
        return null;
    }
}
