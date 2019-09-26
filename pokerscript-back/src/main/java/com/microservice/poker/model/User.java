package com.microservice.poker.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode
@ToString
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Integer id;
    private String username;
    private String password;
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    private Integer active;
    private String name;
    private String surname;
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "roles_id")
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "stats_id")
    private Stat stats;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "countries_id")
    private Country country;

    @PrePersist
    public void changeValues() {
        this.created = LocalDateTime.now();
        this.active = 1;
    }
}
