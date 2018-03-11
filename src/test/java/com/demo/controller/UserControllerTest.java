package com.demo.controller;

import com.demo.Application;
import com.demo.domain.income.UserDataRequest;
import com.demo.domain.outcome.UserDataResponse;
import com.demo.model.User;
import com.demo.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class UserControllerTest {

    @Mock
    private UserService userService;

//    @Autowired
    private UserController userController;

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @Before

    public void setupMock() {

        MockitoAnnotations.initMocks(this);

        userController = new UserController(userService);

    }

    @Test
    public void createUserTest() {
        //arrange
        UserDataRequest userDataRequest = new UserDataRequest("name", "login", "surname");
        User user = new User("name", "login", "surname");
        user.setId(1L);
        when(userService.create(userDataRequest)).thenReturn(user);
        //action
        UserDataResponse response = userController.createUser(userDataRequest);
        //assert
        assertEquals(response.getUserId(), user.getId());
        assertEquals(response.getLogin(), user.getLogin());
        assertEquals(response.getName(), user.getName());
        assertEquals(response.getSurname(), user.getSurname());

    }

    @Test
    public void getAllUsersTest() {


    }

    @Test
    public void getBookingTest() {


    }

    @Test
    public void getTotalPriceOfBookingsTest() {


    }

    @Test
    public void getTotalPriceBookingByUserIdTest() {


    }
}
