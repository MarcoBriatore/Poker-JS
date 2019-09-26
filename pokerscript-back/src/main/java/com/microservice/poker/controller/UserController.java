package com.microservice.poker.controller;

import com.microservice.poker.exception.users.EmailRepeatedException;
import com.microservice.poker.exception.users.UserNotFoundException;
import com.microservice.poker.exception.users.UsernameRepeatedException;
import com.microservice.poker.model.User;
import com.microservice.poker.model.UserDTO;
import com.microservice.poker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping("/poker-api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> getUser(@RequestBody UserDTO user) {
        try {
            return new ResponseEntity<>(userService.getUser(user.getUsername(), user.getPassword()), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/updatepassword/{id}")
    public ResponseEntity<?> modifyPassword(@PathVariable("id") final Integer id, @RequestBody String password) {
        try {
            return new ResponseEntity<>(userService.changeUserPassword(id, password.substring(0, password.length() - 1)), HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/updateaccount/{id}")
    public ResponseEntity<?> updateAccount(@PathVariable("id") final Integer id, @RequestBody User u) {
        System.out.println(u.toString());
        return new ResponseEntity<>(userService.updateAccount(id, u), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody User u) {
        try {
            return new ResponseEntity<>(userService.saveUser(u), HttpStatus.CREATED);
        } catch (UsernameRepeatedException e) {
            return new ResponseEntity<>(e, HttpStatus.FOUND);
        } catch (EmailRepeatedException e) {
            return new ResponseEntity<>(e, HttpStatus.FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable("username") final String username) {
        try {
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(userService.getUserByUsername(username));
        } catch (UserNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e);
        }
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable("email") final String email) {
        try {
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(userService.getUserByEmail(email));
        } catch (UserNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e);
        }
    }
}
