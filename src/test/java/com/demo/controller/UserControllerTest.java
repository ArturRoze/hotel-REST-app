package com.demo.controller;

import com.demo.domain.income.UserDataRequest;
import com.demo.domain.outcome.UserDataResponse;
import com.demo.model.Booking;
import com.demo.model.User;
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

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void createUserTest() {

        //arrange
        UserDataRequest userDataRequest = new UserDataRequest();
        userDataRequest.setLogin("iceWoman");
        userDataRequest.setName("Alina");
        userDataRequest.setSurname("Roze");

        //action
        ResponseEntity<UserDataResponse> responseEntity = restTemplate.exchange("/user/create", HttpMethod.POST, new HttpEntity<>(userDataRequest), new ParameterizedTypeReference<UserDataResponse>() {
        });

        //assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        UserDataResponse body = responseEntity.getBody();
        assertNotNull(body.getUserId());
        assertEquals(userDataRequest.getLogin(), body.getLogin());
        assertEquals(userDataRequest.getName(), body.getName());
        assertEquals(userDataRequest.getSurname(), body.getSurname());
    }

    @Test
    public void getAllUsersTest() throws Exception {

        //action
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange("/user/all", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        });

        //assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        List<User> body = responseEntity.getBody();
        assertNotNull(body);
        assertEquals(5, body.size());
        User user = body.get(0);
        assertEquals(Long.valueOf(1L), user.getId());
        assertEquals("sky", user.getLogin());
        assertEquals("Artur", user.getName());
        assertEquals("Roze", user.getSurname());
    }

    @Test
    public void getBookingTest() {

        //action
        ResponseEntity<List<Booking>> responseEntity = restTemplate.exchange("/user/3/booking", HttpMethod.GET, null, new ParameterizedTypeReference<List<Booking>>() {
        });

        //assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        List<Booking> body = responseEntity.getBody();
        assertNotNull(body);
        assertEquals(1, body.size());
        Booking booking = body.get(0);
        assertEquals(Double.valueOf(385), booking.getTotalPrice());
        assertEquals(Long.valueOf(3), booking.getUserId());
        assertEquals(Long.valueOf(3), booking.getRoomId());
    }

    @Test
    public void getTotalPriceOfBookingsTest() {

        //action
        ResponseEntity<Double> responseEntity = restTemplate.exchange("/user/1/bookings/total_price", HttpMethod.GET, null, new ParameterizedTypeReference<Double>() {
        });

        //assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        Double body = responseEntity.getBody();
        assertNotNull(body);
        assertEquals(Double.valueOf(50), body);
    }

    @Test
    public void getTotalPriceBookingByUserIdTest() {

        //action
        ResponseEntity<Double> responseEntity = restTemplate.exchange("/user/1/booking/1/total_price", HttpMethod.GET, null, new ParameterizedTypeReference<Double>() {
        });

        //assert
        assertEquals(200, responseEntity.getStatusCodeValue());
        Double body = responseEntity.getBody();
        assertNotNull(body);
        assertEquals(Double.valueOf(50), body);
    }
}
