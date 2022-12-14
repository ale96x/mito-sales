package com.mitocode.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduct;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(columnDefinition = "decimal(6,2)", nullable = false) //columnDefinition para establecer el tipo de dato a la columna
    private Double price;

    @Column(length = 150, nullable = false)
    private String description;

    @Column(nullable = false)
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "id_category", nullable = false, foreignKey = @ForeignKey(name = "fk_product_category"))
    private Category category;
}
