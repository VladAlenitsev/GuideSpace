package com.guidespace.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE, reason = "Email address already in use")
public class DuplicateEmailException extends Exception {
    DuplicateEmailException(String message) {
        super(message);
    }
}