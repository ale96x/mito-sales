package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.mitocode.model.Product;
import com.mitocode.model.Sale;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleDetailDTO {
    @JsonBackReference
    private SaleDTO sale;

    @JsonIncludeProperties(value = {"idProduct","name"})
    private ProductDTO product;
    private Short quantity;
    private Double salePrice;
    private Double discount;
}
