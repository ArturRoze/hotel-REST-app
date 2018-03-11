package com.demo.controller;

import com.demo.Application;
import com.demo.service.RoomService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@ActiveProfiles("test")
public class RoomControllerTest {

    @Autowired
    private RoomService roomService;

    @InjectMocks
    RoomController roomController;

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @Test
    public void getAllAvailableRoomsTest() {


    }

    @Test
    public void getRoomsOfCategoryTest() {


    }

    @Test
    public void bookRoomOnDateTest() {


    }
}
