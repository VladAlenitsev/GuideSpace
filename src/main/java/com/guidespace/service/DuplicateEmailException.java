package com.guidespace.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Sebastian on 7.07.2016.
 **/
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Email address already in use")
public class DuplicateEmailException extends Exception {
    DuplicateEmailException(String message) {
        super(message);
    }
}