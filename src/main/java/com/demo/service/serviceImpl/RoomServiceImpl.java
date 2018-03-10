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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Service for managing {@link Room} in repository
 *
 * @author Artur
 * @see RoomService
 */
@Service
public class RoomServiceImpl implements RoomService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final RoomRepository roomRepository;

    private final AdditionalOptionRepository additionalOptionRepository;

    private final BookingRepository bookingRepository;

    @Autowired
    public RoomServiceImpl(RoomRepository roomRepository, AdditionalOptionRepository additionalOptionRepository, BookingRepository bookingRepository) {
        this.roomRepository = roomRepository;
        this.additionalOptionRepository = additionalOptionRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    @Transactional
    public List<Room> getAllAvailableRoom(PeriodBookRequest periodBookRequest) {
        LOGGER.info("get all available room on date: {} ", periodBookRequest);
        return roomRepository.getAllAvailableRoomsOnPeriod(periodBookRequest.getStartDate(), periodBookRequest.getEndDate());
    }

    @Override
    @Transactional
    public List<Room> getRoomsOfCategory(Category nameCategory) {
        LOGGER.info("get room of category: {}", nameCategory);
        return roomRepository.findAllByCategory(nameCategory);
    }

    @Override
    @Transactional
    public BookResponse bookRoom(BookRequest bookRequest) {
        LOGGER.info("user with id {} books room with id: {}", bookRequest.getUserId(), bookRequest.getRoomId());
        Long userId = bookRequest.getUserId();
        Room requiredRoom = roomRepository.findOne(bookRequest.getRoomId());
        Long idRoom = requiredRoom.getId();
        Category category = requiredRoom.getCategory();

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

        Timestamp startDate = bookRequest.getStartDate();
        Timestamp endDate = bookRequest.getEndDate();
        Long countBookingsDay = endDate.getTime() - startDate.getTime();
        Long days = TimeUnit.MILLISECONDS.toDays(countBookingsDay);
        if (days == 0) {
            days = 1L;
        }
        Double totalPrice = days * requiredRoom.getPrice() + totalPriceAdditionalOptions;

        Booking booking = new Booking(totalPrice, startDate, endDate, userId, idRoom);
        booking.setAdditionalOptions(allAdditionalOptionsForBooking);
        Booking savedBooking = bookingRepository.save(booking);

        return new BookResponse(userId, idRoom, savedBooking.getId(), totalPrice, category, bookRequest.getStartDate(), bookRequest.getEndDate(), allAdditionalOptionsForBooking);
    }
}
