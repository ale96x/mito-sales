package com.mitocode.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "role")
public class Role {
    @EqualsAndHashCode.Include
    @Id //Se generara de forma manual
    private Integer idRole;

    @Column(length = 100,nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean enabled;


}
