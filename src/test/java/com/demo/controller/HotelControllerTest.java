package com.demo.controller;

import com.demo.model.Booking;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class HotelControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllBookingsByHotelIdTest() {

        //action
        ResponseEntity<List<Booking>> responseEntity = restTemplate.exchange("/hotel/1/bookings/all", HttpMethod.GET, null, new ParameterizedTypeReference<List<Booking>>() {
        });

        //assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        List<Booking> body = responseEntity.getBody();
        assertNotNull(body);
        assertEquals(5, body.size());

        Booking booking = body.get(0);
        assertEquals(Long.valueOf(1), booking.getUserId());
        assertEquals(Long.valueOf(1), booking.getRoomId());
    }
}
