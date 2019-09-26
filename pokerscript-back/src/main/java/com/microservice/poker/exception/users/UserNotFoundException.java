package com.microservice.poker.exception.users;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String s) {
        super(s);
    }
}
