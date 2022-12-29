package com.mitocode.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Como el procedimiento en al base de datos devuelve una tabla con dos campos se crea un DTO
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProcedureDTO {
    private Integer quantityFn;
    private String dateTimeFn;
}
