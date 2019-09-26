package com.microservice.poker.exception.users;

public class EmailRepeatedException extends Exception {
    public EmailRepeatedException(String s) {
        super(s);
    }
}
