package com.mitocode.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "sale_detail")
public class SaleDetail {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSaleDetail;

    @Column(nullable = false)
    private Short quantity;

    @Column(columnDefinition = "decimal(6,2)")
    private Double salePrice;

    @Column(columnDefinition = "decimal(6,2)")
    private Double discount;

    //Todas la llaves foraneas
    @ManyToOne
    @JoinColumn(name = "id_sale", nullable = false, foreignKey = @ForeignKey(name = "fk_saleDetail_sale"))
    private Sale sale;

    @ManyToOne
    @JoinColumn(name = "id_product", nullable = false, foreignKey = @ForeignKey(name = "fk_saleDetail_product"))
    private Product product;
}
