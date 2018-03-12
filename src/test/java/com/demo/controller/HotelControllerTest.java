package com.demo.controller;

import com.demo.service.HotelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class HotelControllerTest {

    @Mock
    private HotelService hotelService;

    @InjectMocks
    HotelController hotelController;

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @Test
    public void getAllBookingsByHotelIdTest() {


    }
}
