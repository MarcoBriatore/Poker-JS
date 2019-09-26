package com.microservice.poker.service;

import com.microservice.poker.exception.users.EmailRepeatedException;
import com.microservice.poker.exception.users.UserNotFoundException;
import com.microservice.poker.exception.users.UsernameRepeatedException;
import com.microservice.poker.model.Stat;
import com.microservice.poker.model.User;
import com.microservice.poker.model.UserDTO;
import com.microservice.poker.repository.CountryRepository;
import com.microservice.poker.repository.RoleRepository;
import com.microservice.poker.repository.StatsRepository;
import com.microservice.poker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private static final String USER_BY_USERNAME_NOT_FOUND = "No se encontro el usuario: %s";
    private static final String USER_BY_EMAIL_NOT_FOUND = "No se encontro el usuario con el email: %s";
    private static final String USER_BY_ID_NOT_FOUND = "No se encontro el usuario con el id: %s";
    private static final String USERNAME_REPEATED = "Ya hay un usuario con este nombre de usuario: %s";
    private static final String EMAIL_REPEATED = "Ya hay un usuario con este email: %s";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StatsRepository statsRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private RoleRepository roleRepository;

    public User changeUserPassword(Integer id, String password) throws UserNotFoundException {
        User dbUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(String.format(USER_BY_ID_NOT_FOUND, id)));
        dbUser.setPassword(password);
        return userRepository.save(dbUser);
    }

    public User updateAccount(Integer id, User u) {
          return userRepository.save(u);
    }

    public User getUser(String username, String password) throws UserNotFoundException {
        User dbUser = userRepository.findByUsernameAndPassword(username, password);

        if(!Objects.isNull(dbUser)) {
            return dbUser;
        } else {
            throw new UserNotFoundException(String.format(USER_BY_USERNAME_NOT_FOUND, username));
        }
    }

    public User getUserByUsername(String username) throws UserNotFoundException {
        User dbUserByUsername = userRepository.findByUsername(username);

        if(!Objects.isNull(dbUserByUsername)) {
            return dbUserByUsername;
        } else {
            throw new UserNotFoundException(String.format(USER_BY_USERNAME_NOT_FOUND, username));
        }
    }

    public User getUserByEmail(String email) throws UserNotFoundException {
        User dbUserByEmail = userRepository.findByEmail(email);

        if(!Objects.isNull(dbUserByEmail)) {
            return dbUserByEmail;
        } else {
            throw new UserNotFoundException(String.format(USER_BY_EMAIL_NOT_FOUND, email));
        }
    }

    public User saveUser(User u) throws UsernameRepeatedException, EmailRepeatedException {

        if(Objects.isNull(userRepository.findByUsername(u.getUsername()))) {
            if(Objects.isNull(userRepository.findByEmail(u.getEmail()))) {
                System.out.println(u.toString());

                u.setStats(new Stat());
                u.setRole(roleRepository.findByRole("Usuario"));
                return userRepository.save(u);
            } else {
                throw new EmailRepeatedException(String.format(EMAIL_REPEATED, u.getEmail()));
            }
        } else {
            throw new UsernameRepeatedException(String.format(USERNAME_REPEATED, u.getUsername()));
        }
    }
}
