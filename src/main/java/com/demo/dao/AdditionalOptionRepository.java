package com.demo.dao;

import com.demo.model.AdditionalOption;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Specifies methods used to obtain and modify AdditionalOption related information
 * which is stored in the database.
 *
 * @author Artur
 */
public interface AdditionalOptionRepository extends CrudRepository<AdditionalOption, Long> {

    List<AdditionalOption> findByNameIn(List<String> strings);
}
