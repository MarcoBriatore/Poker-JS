package com.microservice.poker.controller;

import com.microservice.poker.exception.stats.StatsNotFound;
import com.microservice.poker.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping("/poker-api/stat")
public class StatsController {
    @Autowired
    private StatsService statsService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getStats(@PathVariable("id") final Integer id) {
        try {
            return new ResponseEntity<>(statsService.getStats(id), HttpStatus.OK);
        } catch (StatsNotFound e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }
}
