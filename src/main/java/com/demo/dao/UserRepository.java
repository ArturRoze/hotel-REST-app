package com.demo.dao;

import com.demo.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User getByLogin(String login);

    User getById(Long id);

    @Override
    Iterable<User> findAll();

//    void deleteAllById(Long id);
}
