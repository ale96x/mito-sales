package com.mitocode.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
    private Integer id;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 30)
    private String nameCategory;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 150)
    //@Pattern(regexp = "[A-Za-Z ]*")
    //@Email
    private String descriptionCategory;

    @NotNull
    private Boolean enabledCategory;
}
