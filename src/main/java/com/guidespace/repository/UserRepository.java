package com.guidespace.repository;


import com.guidespace.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;



public interface UserRepository extends Repository<User, Long> {

    Page<User> findAll(Pageable pageable);

    User findByUsername(String username);

    User save(User user);

    User findByEmailAddress(String emailAddress);

}
