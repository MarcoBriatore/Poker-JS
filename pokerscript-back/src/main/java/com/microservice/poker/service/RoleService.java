package com.microservice.poker.service;

import com.microservice.poker.exception.roles.RoleNotFoundException;
import com.microservice.poker.model.Role;
import com.microservice.poker.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class RoleService {
    private static final String ROLE_BY_ROLE_NOT_FOUND = "No se encontro el rol con nombre: %s";

    @Autowired
    private RoleRepository roleRepository;

    public Role getRoleByRole(String role) throws RoleNotFoundException {
        Role dbRoleByRole = roleRepository.findByRole(role);

        if(!Objects.isNull(dbRoleByRole)) {
            return dbRoleByRole;
        } else {
            throw new RoleNotFoundException(String.format(ROLE_BY_ROLE_NOT_FOUND, role));
        }
    }
}
