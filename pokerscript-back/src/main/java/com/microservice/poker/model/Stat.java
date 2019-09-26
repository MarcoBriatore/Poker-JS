package com.microservice.poker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
@ToString
@Table(name = "stats")
public class Stat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stats_id")
    private Integer id;

    @OneToOne(mappedBy = "stats")
    @JsonIgnore
    private User user;

    private Float founds;
    private Float lost;
    private Float win;
    private Integer tournaments;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime premium_start;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime premium_end;

    private Integer games_played;
    protected Integer premium;

    @PrePersist
    public void changeValues() {
        this.founds = 0.0f;
        this.lost = 0.0f;
        this.win = 0.0f;
        this.premium = 0;
        this.tournaments = 0;
        this.games_played = 0;
        this.premium_end = null;
        this.premium_start = null;
    }
}
