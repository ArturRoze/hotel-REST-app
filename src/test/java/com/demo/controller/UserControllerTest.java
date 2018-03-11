package com.demo.controller;

import com.demo.Application;
import com.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private UserService userService;

    @InjectMocks
    UserController userController;

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @Test
    public void createUserTest() {


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
