package com.microservice.poker.controller;

import com.microservice.poker.exception.countries.CountryNotFoundException;
import com.microservice.poker.exception.countries.EmptyCountriesException;
import com.microservice.poker.model.Country;
import com.microservice.poker.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/poker-api/countries")
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping("")
    public ResponseEntity<?> getAllCountries() {
        try {
            return new ResponseEntity<>(countryService.getAll(), HttpStatus.OK);
        } catch (EmptyCountriesException e) {
            return new ResponseEntity<>(e, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{name]")
    public ResponseEntity<?> getCountryByName(@PathVariable("name") final String name) {
        try {
            return new ResponseEntity<>(countryService.getCountryByName(name), HttpStatus.OK);
        } catch (CountryNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{code}")
    public ResponseEntity<?> getCountryByCode(@PathVariable("code") final String code) {
        try {
            return new ResponseEntity<>(countryService.getCountryByCode(code), HttpStatus.OK);
        } catch (CountryNotFoundException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
