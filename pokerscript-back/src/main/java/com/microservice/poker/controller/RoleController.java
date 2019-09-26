package com.microservice.poker.controller;

import com.microservice.poker.exception.roles.RoleNotFoundException;
import com.microservice.poker.model.Role;
import com.microservice.poker.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/poker-api/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/{name}")
    public ResponseEntity<?> getRoleByRole(@PathVariable("name") String role) {
        try {
            return ResponseEntity
                    .status(HttpStatus.FOUND)
                    .body(roleService.getRoleByRole(role));
        } catch (RoleNotFoundException e) {
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
