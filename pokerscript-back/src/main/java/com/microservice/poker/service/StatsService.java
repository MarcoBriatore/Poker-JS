package com.microservice.poker.service;

import com.microservice.poker.exception.stats.StatsNotFound;
import com.microservice.poker.model.Stat;
import com.microservice.poker.repository.StatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {
    private static final String STATS_BY_ID_NOT_FOUND = "No se encontraron estadisticas con el id: %s";

    @Autowired
    private StatsRepository statsRepository;

    public Stat getStats(Integer id) throws StatsNotFound {
        return statsRepository.findById(id).orElseThrow(() -> new StatsNotFound(String.format(STATS_BY_ID_NOT_FOUND, id)));
    }
}
