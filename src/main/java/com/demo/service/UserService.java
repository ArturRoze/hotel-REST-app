package com.demo.service;

import com.demo.domain.income.UserDataRequest;
import com.demo.model.Room;
import com.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> getAll();

    User create(UserDataRequest userDataRequest);

    List<Room> readAllBookingOfUser(Long id);

    Double getTotalPriceOfBooking(Long id);
}
