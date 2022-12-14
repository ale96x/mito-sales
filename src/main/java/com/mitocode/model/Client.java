package com.mitocode.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "client")
public class Client {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClient;

    @Column(length = 100,nullable = false) //Longitud de 100 y no permite null
    private String firstName;

    @Column(length = 100,nullable = false)
    private String lastName;

    @Column(length = 10,nullable = false)
    private String cardId;

    @Column(length = 10,nullable = false)
    private String phoneNumber;

    @Column(length = 50,nullable = false)
    private String email;

    @Column(length = 100,nullable = false)
    private String address;
}
