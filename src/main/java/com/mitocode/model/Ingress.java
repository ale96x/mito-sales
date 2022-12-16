package com.mitocode.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "ingress")
public class Ingress{
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIngress;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private Double total;

    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private Double tax;

    @Column(length = 10, nullable = false)
    private String serialNumber;

    @Column(nullable = false)
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "id_provider", nullable = false, foreignKey = @ForeignKey(name = "fk_ingress_provider"))
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "fk_ingress_user"))
    private User user;
}
