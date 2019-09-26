package com.microservice.poker.exception.users;

public class UsernameRepeatedException extends Exception {
    public UsernameRepeatedException(String s) {
        super(s);
    }
}
