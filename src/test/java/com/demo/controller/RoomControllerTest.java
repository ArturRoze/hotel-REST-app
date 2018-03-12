package com.demo.controller;

import com.demo.domain.income.PeriodBookRequest;
import com.demo.model.Room;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class RoomControllerTest {


    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllAvailableRoomsTest() {

        PeriodBookRequest periodBookRequest = new PeriodBookRequest();
        periodBookRequest.setStartDate(Timestamp.from(Instant.now()));
        periodBookRequest.setEndDate(Timestamp.from(Instant.now().plus(1, ChronoUnit.DAYS)));

        ResponseEntity<List<Room>> responseEntity = restTemplate.exchange("/rooms/all", HttpMethod.POST, new HttpEntity<>(periodBookRequest), new ParameterizedTypeReference<List<Room>>() {
        });
        assertEquals(200, responseEntity.getStatusCodeValue());
        List<Room> body = responseEntity.getBody();
        assertNotNull(body);
        assertEquals(5, body.size());

        Room room = body.get(0);
        assertEquals(Double.valueOf(50), room.getPrice());
        assertEquals(Integer.valueOf(1), room.getNumber());
    }

    @Test
    public void getRoomsOfCategoryTest() {


    }

    @Test
    public void bookRoomOnDateTest() {


    }
}

