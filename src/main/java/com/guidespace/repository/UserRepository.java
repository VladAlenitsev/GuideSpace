package com.guidespace.repository;


import com.guidespace.domain.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import java.util.List;


public interface UserRepository extends Repository<Person, Long> {

    Page<Person> findAll(Pageable pageable);

    Person findByUsername(String username);
    Person findById(long id);

    Person save(Person user);

    Person findByEmailAddress(String emailAddress);

    List<Person> findAll();
}
