package com.demo.service.impl;

import com.demo.dao.BookingRepository;
import com.demo.dao.UserRepository;
import com.demo.service.serviceImpl.UserServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @BeforeClass
    public static void beforeClass() {
        initMocks(UserServiceImpl.class);
    }

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Test
    public void getAllTest() {
    }

    @Test
    public void createTest() {
    }

    @Test
    public void readAllBookingOfUserTest() {
    }

    @Test
    public void getTotalPriceOfBookingsTest() {
    }

    @Test
    public void getTotalPriceBookingByUserIdTest() {
    }
}
