package com.demo.dao;

import com.demo.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Specifies methods used to obtain and modify {@link User} related information
 * which is stored in the database.
 *
 * @author Artur
 */
public interface UserRepository extends CrudRepository<User, Long> {

    User getByLogin(String login);
}
