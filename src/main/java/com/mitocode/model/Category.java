package com.mitocode.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "category")
public class Category {
    @EqualsAndHashCode.Include //Solo toma el id como criterio
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@SequenceGenerator() para seleccionar una secuencia personalizada
    private Integer idCategory;
    @Column(length = 50)
    private String name;
    @Column(length = 150)
    private String description;
    @Column(nullable = false)
    private Boolean enabled;
}
