package com.microservice.poker.repository;

import com.microservice.poker.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    Country findByCode(String code);
    Country findByName(String name);
}