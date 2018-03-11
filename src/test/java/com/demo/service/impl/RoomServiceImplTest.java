package com.demo.service.impl;

import com.demo.dao.AdditionalOptionRepository;
import com.demo.dao.BookingRepository;
import com.demo.dao.RoomRepository;
import com.demo.domain.Category;
import com.demo.domain.income.BookRequest;
import com.demo.domain.income.PeriodBookRequest;
import com.demo.domain.outcome.BookResponse;
import com.demo.model.AdditionalOption;
import com.demo.model.Booking;
import com.demo.model.Room;
import com.demo.service.serviceImpl.RoomServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class RoomServiceImplTest {

    public RoomServiceImplTest() {
    }

    @BeforeClass
    public static void beforeClass() {
        initMocks(RoomServiceImpl.class);
    }

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private AdditionalOptionRepository additionalOptionRepository;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private BookRequest bookRequest;

    @Mock
    private Room room;

    @Mock
    private PeriodBookRequest periodBookRequest;

    @Mock
    private AdditionalOption additionalOption;

    @InjectMocks
    private RoomServiceImpl roomService;

    @Test
    public void getAllAvailableRoomTest() {

        List<Room> rooms = Collections.singletonList(room);
        Timestamp startDate = mock(Timestamp.class);
        Timestamp endDate = mock(Timestamp.class);
        periodBookRequest.setStartDate(startDate);
        periodBookRequest.setEndDate(endDate);

        when(roomRepository.getAllAvailableRoomsOnPeriod(periodBookRequest.getStartDate(), periodBookRequest.getEndDate())).thenReturn(rooms);

        List<Room> allAvailableRoomsOnPeriod = roomService.getAllAvailableRoom(periodBookRequest);
        assertEquals(allAvailableRoomsOnPeriod, rooms);
        verify(roomRepository).getAllAvailableRoomsOnPeriod(periodBookRequest.getStartDate(), periodBookRequest.getEndDate());
    }

    @Test
    public void getRoomsOfCategoryTest() {

        room.setCategory(Category.SINGLE);
        List<Room> rooms = Collections.singletonList(room);
        when(roomRepository.findAllByCategory(room.getCategory())).thenReturn(rooms);

        List<Room> expectedRoomsByCategory = roomService.getRoomsOfCategory(room.getCategory());

        assertEquals(expectedRoomsByCategory, rooms);
        verify(roomRepository).findAllByCategory(room.getCategory());
    }

    @Test
    public void bookRoomTest() {


    }
}
