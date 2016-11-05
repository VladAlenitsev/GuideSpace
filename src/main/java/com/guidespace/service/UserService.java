package com.guidespace.service;


import com.guidespace.domain.Person;
import com.guidespace.repository.UserRepository;
import com.guidespace.utils.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Value("${crypto.algorithm}")
    private String algorithm;

    @Autowired
    private UserRepository userRepository;

    //makes login
    public boolean authenticate(String username, String password) {
        Person user = userRepository.findByUsername(username);
        if (user != null) {
            if (SecurityUtility.hashPassword(password, user.getPasswordSalt(), algorithm).equals(user.getPasswordHash())) {
                return true;
            }
        }
        return false;

    }

    public void register(String username, String password, String email) throws DuplicateUsernameException, DuplicateEmailException {
        if (userRepository.findByUsername(username) != null) {
            throw new DuplicateUsernameException("Username already in use");
        } else if (userRepository.findByEmailAddress(email) != null) {
            throw new DuplicateEmailException("Email address already in use!");
        } else {
            String salt = SecurityUtility.generateSalt();
            String passwordHash = SecurityUtility.hashPassword(password, salt, algorithm);
            Person newUser = new Person(username, passwordHash, salt, email);
            newUser.setUser_role_id(1);
            userRepository.save(newUser);
        }
    }




    public List<Person> getUsers() {
        return userRepository.findAll();
    }

    public Person getUser(String username) {
        return userRepository.findByUsername(username);
    }


    public void update(Person person) {
        userRepository.save(person);
    }

}
