package com.demo.service;

import com.demo.domain.income.UserDataRequest;
import com.demo.model.Booking;
import com.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> getAll();

    User create(UserDataRequest userDataRequest);

    List<Booking> readAllBookingOfUser(Long id);

    Double getTotalPriceOfBookings(Long id);

    Double getTotalPriceOfBooking(Long userId, Long bookingId);
}
