package com.mitocode.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ingress_detail")
@IdClass(IngressDetailPK.class)
public class IngressDetail {
    @Id
    private Ingress ingress;

    @Id
    private Product product;

    @Column(nullable = false)
    private Short quantity;

    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private Short cost;
}
