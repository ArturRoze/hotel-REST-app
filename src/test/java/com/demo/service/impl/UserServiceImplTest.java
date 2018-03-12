package com.demo.service.impl;

import com.demo.dao.BookingRepository;
import com.demo.dao.UserRepository;
import com.demo.domain.income.UserDataRequest;
import com.demo.model.User;
import com.demo.service.serviceImpl.UserServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.when;
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
//        //arrange
//        UserDataRequest userDataRequest = getUserDataRequest();
//        User savedUser = getTestUser();
//        savedUser.setId(1L);
//
//        when(userRepository.getByLogin(getUserDataRequest().getLogin())).thenReturn(savedUser);
//
//        //action
//        User expectedUser = userService.create(getUserDataRequest());
//
//        //assert
//        assertEquals()
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

    private User getTestUser() {
        User user = new User("login", "name", "surname");
        return user;
    }

    private UserDataRequest getUserDataRequest() {
         return new UserDataRequest("name", "login", "surname");
    }
}
