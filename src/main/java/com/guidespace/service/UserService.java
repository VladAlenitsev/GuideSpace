package com.guidespace.service;


import com.guidespace.domain.Person;
import com.guidespace.repository.UserRepository;
import com.guidespace.utils.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Service
public class UserService {

    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

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

    public void register(String username, String password, String email, String name, String surname,
                         String userBirthDate, String certWorkLangs, String active_cert_location, String cert_exp_date) throws Exception {
        if (userRepository.findByUsername(username) != null) {
            throw new DuplicateUsernameException("Username already in use");
        } else if (userRepository.findByEmailAddress(email) != null) {
            throw new DuplicateEmailException("Email address already in use!");
        } else {
            String salt = SecurityUtility.generateSalt();
            String passwordHash = SecurityUtility.hashPassword(password, salt, algorithm);
            Date userBirthDateD = null;
            Date cert_exp_dateD = null;
            try {
                userBirthDateD = df.parse(userBirthDate);
                cert_exp_dateD = df.parse(cert_exp_date);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
            if (!(userBirthDateD == null && cert_exp_dateD == null)) {
                Person newUser = new Person(username, passwordHash, salt, email, name, surname, userBirthDateD, certWorkLangs, active_cert_location, cert_exp_dateD);
                newUser.setUser_role_id(1);
                userRepository.save(newUser);
            }
            else
                throw new Exception();

        }
    }

    public List<Person> getUsers() {
        return userRepository.findAll();
    }

    public Person getUser(String username) {
        return userRepository.findByUsername(username);
    }


    public Person getUser(long id) {
        return userRepository.findById(id);
    }

    public void update(Person person) {
        userRepository.save(person);
    }

}
