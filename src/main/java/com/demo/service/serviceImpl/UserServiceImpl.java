package com.demo.service.serviceImpl;

import com.demo.dao.RoomRepository;
import com.demo.dao.UserRepository;
import com.demo.domain.income.UserDataRequest;
import com.demo.model.AdditionalOption;
import com.demo.model.Room;
import com.demo.model.User;
import com.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;

    private final RoomRepository roomRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoomRepository roomRepository) {
        this.userRepository = userRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public List<User> getAll() {
         return (List<User>) userRepository.findAll();
    }

    @Override
    @Transactional
    public User create(UserDataRequest userDataRequest) {
        LOGGER.info("Save user: {}", userDataRequest);
        User existingUser = userRepository.getByLogin(userDataRequest.getLogin());
        if (existingUser != null) {
            return existingUser;
        }
        User user = new User(userDataRequest.getLogin(), userDataRequest.getName(), userDataRequest.getSurname());

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public List<Room> readAllBookingOfUser(Long userId) {
        LOGGER.info("Room List of user with id: {}", userId);
        List<Room> allRoomsByUserId = roomRepository.findAllByUserId(userId);
        if (allRoomsByUserId == null){
            LOGGER.info("user with id: {} is not exist", userId);
        }
        return allRoomsByUserId;
    }

//    @Override
//    @Transactional
//    public Double getTotalPriceOfBooking(Long id) {
//
//        User user = userRepository.getById(id);
//        List<Room> rooms = user.getRooms();
//        Double priceBookingRooms = getPriceBookingRooms(rooms);
//        Double priceAdditionalOptions = getPriceAdditionalOptions(rooms);
//        return priceBookingRooms + priceAdditionalOptions;
//    }
//
//    private Double getPriceBookingRooms(List<Room> rooms) {
//        Double totalPriceRooms = 0.0;
//        for (Room room : rooms) {
//            totalPriceRooms += room.getPrice();
//        }
//        return totalPriceRooms;
//    }
//
//    private Double getPriceAdditionalOptions(List<Room> rooms) {
//        Double totalPriceOptions = 0.0;
//        for (Room room : rooms) {
//            List<AdditionalOption> additionalOptions = room.getAdditionalOptions();
//            for (AdditionalOption additionalOption : additionalOptions) {
//                totalPriceOptions += additionalOption.getPrice();
//            }
//        }
//        return totalPriceOptions;
//    }
}
