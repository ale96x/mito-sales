package com.mitocode.security;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

//Clase Seguridad 4
@Getter
@AllArgsConstructor
public class JwtResponse {
    private final String jwtToken;
}
