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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
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
    private Room room;

    @Mock
    private PeriodBookRequest periodBookRequest;

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

    @Test //TODO
    public void bookRoomTest() {
        //arrange
        Room roomFromRepository = getDummyRoom();
        roomFromRepository.setId(2L);
        BookRequest bookRequest = getDummyBookRequest();
        when(roomRepository.findOne(bookRequest.getRoomId())).thenReturn(roomFromRepository);
        bookRequest.setAdditionalOptions(getDummyAdditionalOptions());
        List<AdditionalOption> additionalOptionsInRepositoryFromRequest = getDummyAdditionalOptions();
        when(additionalOptionRepository.findByNameIn(Collections.singletonList(bookRequest.getAdditionalOptions().get(0).getName()))).thenReturn(additionalOptionsInRepositoryFromRequest);
        Double dummyTotalPrice = 150.0;
        Booking booking = getDummyBooking(roomFromRepository, dummyTotalPrice);
        booking.setAdditionalOptions(additionalOptionsInRepositoryFromRequest);
        Booking savedBooking = getDummyBooking(roomFromRepository, dummyTotalPrice);
        savedBooking.setId(12L);
        when(bookingRepository.save(booking)).thenReturn(savedBooking);

        //action
        BookResponse actualBookResponse = roomService.bookRoom(bookRequest);

        //assert
        BookResponse expectedBookResponse = new BookResponse(bookRequest.getUserId(), roomFromRepository.getId(), savedBooking.getId(), dummyTotalPrice, roomFromRepository.getCategory(), bookRequest.getStartDate(), bookRequest.getEndDate(), additionalOptionsInRepositoryFromRequest);
        assertEquals(expectedBookResponse, actualBookResponse);
    }

    private Booking getDummyBooking(Room room, Double dummyTotalPrice) {
        return new Booking(dummyTotalPrice, getDummyBookRequest().getStartDate(), getDummyBookRequest().getEndDate(), getDummyBookRequest().getUserId(), room.getId());
    }

    private List<AdditionalOption> getDummyAdditionalOptions() {
        AdditionalOption additionalOption = new AdditionalOption("clean", 10);
        return Collections.singletonList(additionalOption);
    }

    private Room getDummyRoom() {
        return new Room(7, Category.SINGLE, 100.0, 1L);
    }

    private BookRequest getDummyBookRequest() {
        Timestamp startDate = new Timestamp(System.currentTimeMillis());
        Timestamp endDate = new Timestamp(System.currentTimeMillis() + 86400003);
        return new BookRequest(1L, 2L, startDate, endDate);
    }
}
