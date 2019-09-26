package com.microservice.poker.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
public class UserDTO {
    private String username;
    private String password;
}
