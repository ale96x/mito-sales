package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mitocode.model.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO {
    private Integer idProduct;
    private Integer idCategory;
    private String name;
    private Double price;
    private String description;
    private Boolean enabled;
}
