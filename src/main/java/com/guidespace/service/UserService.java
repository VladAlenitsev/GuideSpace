package com.guidespace.service;

import com.cgi.domain.User;
import com.cgi.repository.UserRepository;
import com.cgi.utils.SecurityUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by Sebastian on 6.07.2016.
 **/


@Service
public class UserService {
    @Value("${crypto.algorithm}")
    private String algorithm;

    @Autowired
    private UserRepository userRepository;

    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
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
            User newUser = new User(username, passwordHash, salt, email);
            userRepository.save(newUser);
        }
    }


}
