package com.guidespace.security;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/rest")
public class SecurityController {


    @RequestMapping(value = "/getCSRF", method = RequestMethod.GET)
    public ResponseEntity<SecurityController> getCSRF() {
        return new ResponseEntity<>(HttpStatus.OK);
    }


    private static class RequestResponse {
        private String message;

        RequestResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
