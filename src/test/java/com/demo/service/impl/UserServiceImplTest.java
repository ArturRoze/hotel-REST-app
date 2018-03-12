package com.demo.service.impl;

import com.demo.dao.BookingRepository;
import com.demo.dao.UserRepository;
import com.demo.domain.income.UserDataRequest;
import com.demo.model.Booking;
import com.demo.model.User;
import com.demo.service.serviceImpl.UserServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @BeforeClass
    public static void beforeClass() {
        initMocks(UserServiceImpl.class);
    }

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Test
    public void getAllTest() {

        //arrange
        List<User> expectedUsers = Collections.singletonList(getTestUser());

        when(userRepository.findAll()).thenReturn(expectedUsers);
        //action
        List<User> actualUsers = userService.getAll();

        //assert
        assertEquals(expectedUsers, actualUsers);
        verify(userRepository).findAll();
    }

    @Test
    public void createTest() {
        //arrange
        UserDataRequest userDataRequest = getUserDataRequest();
        User userRequest = new User(userDataRequest.getLogin(), userDataRequest.getName(), userDataRequest.getSurname());
        User expectedUser = getTestUser();
        expectedUser.setId(1L);

        when(userRepository.getByLogin(getUserDataRequest().getLogin())).thenReturn(null);
        when(userRepository.save(userRequest)).thenReturn(expectedUser);

        //action
        User actualUser = userService.create(getUserDataRequest());

        //assert
        assertEquals(expectedUser, actualUser);
        verify(userRepository).getByLogin(getUserDataRequest().getLogin());
        verify(userRepository).save(userRequest);
    }

    @Test
    public void readAllBookingOfUserTest() {

        //arrange
        Booking booking = new Booking();
        List<Booking> expectedBookings = Collections.singletonList(booking);
        User testUser = getTestUser();
        testUser.setId(5L);

        when(bookingRepository.findAllByUserId(testUser.getId())).thenReturn(expectedBookings);

        //action
        List<Booking> actualBookings = userService.readAllBookingOfUser(testUser.getId());

        //assert
        assertEquals(expectedBookings, actualBookings);
        verify(bookingRepository).findAllByUserId(5L);
    }

    @Test
    public void getTotalPriceOfBookingsTest() {

        //arrange
        Booking booking = new Booking();
        booking.setId(4L);
        booking.setTotalPrice(150.0);
        User testUser = getTestUser();
        testUser.setId(12L);
        List<Booking> expectedBooking = Collections.singletonList(booking);
        Double expectedTotalPrice = booking.getTotalPrice();
        when(bookingRepository.findAllByUserId(12L)).thenReturn(expectedBooking);

        //action
        Double actualTotalPrice = userService.getTotalPriceOfBookings(testUser.getId());

        //assert
        assertEquals(expectedTotalPrice, actualTotalPrice);
        verify(bookingRepository).findAllByUserId(testUser.getId());
    }

    @Test
    public void getTotalPriceBookingByUserIdTest() {

        Booking booking = new Booking();
        booking.setId(21L);
        User testUser = getTestUser();
        testUser.setId(1L);
        Double expectedTotalPrice = 100.0;

        //arrange
        when(bookingRepository.getTotalPriceBookingByUserId(testUser.getId(), booking.getId())).thenReturn(expectedTotalPrice);

        //action
        Double actualTotalPrice = userService.getTotalPriceBookingByUserId(testUser.getId(), booking.getId());

        //assert
        assertEquals(expectedTotalPrice, actualTotalPrice);
        verify(bookingRepository).getTotalPriceBookingByUserId(testUser.getId(), booking.getId());
    }

    private User getTestUser() {
        return new User("login", "name", "surname");
    }

    private UserDataRequest getUserDataRequest() {
        return new UserDataRequest("name", "login", "surname");
    }
}
