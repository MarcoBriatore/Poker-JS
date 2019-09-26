package com.microservice.poker.repository;

import com.microservice.poker.model.Stat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatsRepository extends JpaRepository<Stat, Integer> {
}
