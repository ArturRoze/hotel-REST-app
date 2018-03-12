package com.demo.controller;

import com.demo.domain.Category;
import com.demo.domain.income.BookRequest;
import com.demo.domain.income.PeriodBookRequest;
import com.demo.domain.outcome.BookResponse;
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

import static org.junit.Assert.assertArrayEquals;
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

        //arrange
        PeriodBookRequest periodBookRequest = new PeriodBookRequest();
        periodBookRequest.setStartDate(Timestamp.from(Instant.now()));
        periodBookRequest.setEndDate(Timestamp.from(Instant.now().plus(1, ChronoUnit.DAYS)));

        //action
        ResponseEntity<List<Room>> responseEntity = restTemplate.exchange("/rooms/all", HttpMethod.POST, new HttpEntity<>(periodBookRequest), new ParameterizedTypeReference<List<Room>>() {
        });

        //assert
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

        //action
        ResponseEntity<List<Room>> responseEntity = restTemplate.exchange("/rooms/category?nameCategory=SINGLE", HttpMethod.GET, null, new ParameterizedTypeReference<List<Room>>() {
        });

        //assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        List<Room> body = responseEntity.getBody();
        assertEquals(2, body.size());
        Room room = body.get(0);
        assertEquals(Integer.valueOf(1), room.getNumber());
        assertEquals(Category.SINGLE, room.getCategory());

    }

    @Test
    public void bookRoomOnDateTest() {

        //arrange
        BookRequest bookRequest = new BookRequest();
        bookRequest.setUserId(5L);
        bookRequest.setRoomId(2L);
        bookRequest.setStartDate(Timestamp.from(Instant.now().plus(20, ChronoUnit.DAYS)));
        bookRequest.setEndDate(Timestamp.from(Instant.now().plus(22, ChronoUnit.DAYS)));

        //action
        ResponseEntity<BookResponse> responseEntity = restTemplate.exchange("/rooms/book", HttpMethod.POST, new HttpEntity<>(bookRequest), new ParameterizedTypeReference<BookResponse>() {
        });

        //assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        BookResponse body = responseEntity.getBody();
        assertNotNull(body.getBookingId());
        assertEquals(Long.valueOf(50), body.getUserId());
        assertEquals(Long.valueOf(1), body.getRoomId());
    }
}

