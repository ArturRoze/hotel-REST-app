package com.demo.service.serviceImpl;

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
import com.demo.service.RoomService;
import org.h2.util.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class RoomServiceImpl implements RoomService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final RoomRepository roomRepository;

    private AdditionalOptionRepository additionalOptionRepository;

    private final BookingRepository bookingRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.bookingRepository = bookingRepository;
    }

    //WORKs
    @Override
    @Transactional
    public List<Room> getAllAvailableRoom(PeriodBookRequest periodBookRequest) {
        LOGGER.info("get all available room on date: {} ", periodBookRequest);
        return roomRepository.getAllAvailableRoomsOnPeriod(periodBookRequest.getStartDate(), periodBookRequest.getEndDate());
    }

    //WORKs
    @Override
    @Transactional
    public List<Room> getRoomsOfCategory(Category nameCategory) {
        LOGGER.info("get room of category: {}", nameCategory);
        return roomRepository.findAllByCategory(nameCategory);

    }

    //Надо разделить на методы !!!
    @Override
    @Transactional
    public BookResponse bookRoom(BookRequest bookRequest) {
        LOGGER.info("user with id {} books room with category: {}", bookRequest.getUserId(), bookRequest.getCategory());
        Long userId = bookRequest.getUserId();

        List<Room> allAvailableRoomsOnPeriod = roomRepository.getAllAvailableRoomsOnPeriod(bookRequest.getStartDate(), bookRequest.getEndDate());

        Long idSuitableRoom = 0L;
        Double priceRoom = 0.0;
        for (Room room : allAvailableRoomsOnPeriod) {
            if (room.getCategory().equals(bookRequest.getCategory())){
                idSuitableRoom = room.getId();
                priceRoom = room.getPrice();
                break;
            }
        }

        Timestamp startDate = bookRequest.getStartDate();
        Timestamp endDate = bookRequest.getEndDate();
        Long countBookingsDay = endDate.getTime() - startDate.getTime();
        Long days = TimeUnit.MILLISECONDS.toDays(countBookingsDay);// если секунд меньше чем на день 0 дней будет ?
        if (days == 0){
            days = 1L;
        }

        List<AdditionalOption> additionalOptionsByRequest = bookRequest.getAdditionalOptions();

        List<String> namesAdditionalOption = new ArrayList<>();
        for (AdditionalOption additionalOption : additionalOptionsByRequest) {
            namesAdditionalOption.add(additionalOption.getName());
        }

        List<AdditionalOption> allAdditionalOptionsForBooking = additionalOptionRepository.findByNameIn(namesAdditionalOption);

        Double totalPriceAdditionalOptions = 0.0;
        for (AdditionalOption additionalOption : allAdditionalOptionsForBooking) {
            totalPriceAdditionalOptions += additionalOption.getPrice();
        }

        Double totalPrice = days * priceRoom + totalPriceAdditionalOptions;

        Booking booking = new Booking(totalPrice, startDate, endDate, userId, idSuitableRoom);

        booking.setAdditionalOptions(allAdditionalOptionsForBooking);

        Booking savedBooking = bookingRepository.save(booking);

        return new BookResponse(savedBooking.getUserId(), idSuitableRoom, bookRequest.getCategory(), totalPrice, bookRequest.getStartDate(), bookRequest.getEndDate());
    }
}
