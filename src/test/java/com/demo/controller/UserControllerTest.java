package com.demo.controller;

import com.demo.domain.income.UserDataRequest;
import com.demo.model.Booking;
import com.demo.model.User;
import com.demo.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setupMock() {

        MockitoAnnotations.initMocks(this);

        UserController userController = new UserController(userService);

        this.mvc = MockMvcBuilders.standaloneSetup(userController).build();

    }

    @Test
    public void createUserTest() throws Exception {
        UserDataRequest userDataRequest = new UserDataRequest("name", "login", "surname");
        User user = new User("name", "login", "surname");
        user.setId(1L);

        when(userService.create(userDataRequest)).thenReturn(user);

        String createUserJson = getUserDataRequest();

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/create")
                .accept(MediaType.APPLICATION_JSON).content(createUserJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.OK.value(), response.getStatus());

        String expectedContent = "{\"userId\":1,\"login\":\"name\",\"name\":\"login\",\"surname\":\"surname\"}";

        assertEquals(expectedContent, response.getContentAsString());

    }

    @Test
    public void getAllUsersTest() throws Exception {

        User user = new User("name", "login", "surname");
        when(userService.getAll()).thenReturn(Collections.singletonList(user));
        mvc.perform(get("/user/all")).andExpect(status().isOk());
        verify(userService).getAll();

    }


    @Test
    public void getBookingTest() {

        ResponseEntity<List<Booking>> responseEntity = restTemplate.exchange("/user/3/booking", HttpMethod.GET, null, new ParameterizedTypeReference<List<Booking>>() {
        });
        assertEquals(200, responseEntity.getStatusCodeValue());
        List<Booking> body = responseEntity.getBody();
        assertNotNull(body);
        assertEquals(1, body.size());

        Booking booking = body.get(0);
        assertEquals(Double.valueOf(385), booking.getTotalPrice());
        assertEquals(Long.valueOf(3), booking.getUserId());
        assertEquals(Long.valueOf(3), booking.getRoomId());

//        (3,'2018-03-15 12:00:00','2018-03-13 14:00:00',3,3,385)

    }

    @Test
    public void getTotalPriceOfBookingsTest() {


    }

    @Test
    public void getTotalPriceBookingByUserIdTest() {


    }

    private String getUserDataRequest() {
        return "{\n" +
                " \"name\": \"name\",\n" +
                " \"login\": \"login\",\n" +
                " \"surname\": \"surname\"\n" +
                "}";
    }
}
