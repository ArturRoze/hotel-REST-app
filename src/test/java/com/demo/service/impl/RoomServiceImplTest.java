package com.demo.service.impl;

import com.demo.dao.AdditionalOptionRepository;
import com.demo.dao.BookingRepository;
import com.demo.dao.RoomRepository;
import com.demo.service.RoomService;
import com.demo.service.serviceImpl.RoomServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class RoomServiceImplTest {

    @BeforeClass
    public static void beforeClass() {
        initMocks(RoomServiceImpl.class);
    }

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private AdditionalOptionRepository additionalOptionRepository;

//    @Mock
//    private BookingRepository bookingRepository;

    @InjectMocks
    RoomServiceImpl roomService;

    @Test
    public void getAllAvailableRoomTest() {
    }

    @Test
    public void getRoomsOfCategoryTest() {
    }

    @Test
    public void bookRoomTest() {
    }
}
