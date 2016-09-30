package com.guidespace.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Sebastian on 7.07.2016.
 **/
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Username already in use")
public class DuplicateUsernameException extends Exception {
    DuplicateUsernameException(String message) {
        super(message);
    }
}