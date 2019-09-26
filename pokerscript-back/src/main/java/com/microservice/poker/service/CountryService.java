package com.microservice.poker.service;

import com.microservice.poker.exception.countries.CountryNotFoundException;
import com.microservice.poker.exception.countries.EmptyCountriesException;
import com.microservice.poker.model.Country;
import com.microservice.poker.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CountryService {
    private static final String NO_ONE_COUNTRIES_FOUND = "No se han encontrado paises disponibles";
    private static final String COUNTRY_BY_CODE_NOT_FOUND = "No se encontro el pais con el codigo: %s";
    private static final String COUNTRY_BY_NAME_NOT_FOUND = "No se encontro el pais con el nombre: %s";

    @Autowired
    private CountryRepository countryRepository;

    public List<Country> getAll() throws EmptyCountriesException {
        List<Country> list = countryRepository.findAll();

        if(list.size() == 0) {
            throw new EmptyCountriesException(NO_ONE_COUNTRIES_FOUND);
        }

        return list;
    }

    public Country getCountryByCode(String code) throws CountryNotFoundException {
        Country dbCountryByCode = countryRepository.findByCode(code);

        if(!Objects.isNull(dbCountryByCode)) {
            return dbCountryByCode;
        } else {
            throw new CountryNotFoundException(String.format(COUNTRY_BY_CODE_NOT_FOUND, code));
        }
    }

    public Country getCountryByName(String name) throws CountryNotFoundException {
        Country dbCountryByName = countryRepository.findByName(name);

        if(!Objects.isNull(dbCountryByName)) {
            return dbCountryByName;
        } else {
            throw new CountryNotFoundException(String.format(COUNTRY_BY_NAME_NOT_FOUND, name));
        }
    }
}

