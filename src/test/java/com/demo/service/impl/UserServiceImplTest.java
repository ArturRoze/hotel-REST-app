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

import java.util.Collections;
import java.util.List;

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
    UserServiceImpl userService;

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

        //action

        //assert


    }

    @Test
    public void getTotalPriceOfBookingsTest() {

        //arrange

        //action

        //assert

    }

    @Test
    public void getTotalPriceBookingByUserIdTest() {

        //arrange

        //action

        //assert

    }

    private User getTestUser() {
        return new User("login", "name", "surname");
    }

    private UserDataRequest getUserDataRequest() {
        return new UserDataRequest("name", "login", "surname");
    }
}
