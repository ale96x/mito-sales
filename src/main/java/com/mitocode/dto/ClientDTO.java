package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClientDTO {
    private Integer idClient;
    private String firstName;
    private String lastName;
    private String cardId;
    private String phoneNumber;
    private String email;
    private String address;
}
