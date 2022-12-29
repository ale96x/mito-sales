package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mitocode.model.Client;
import com.mitocode.model.SaleDetail;
import com.mitocode.model.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaleDTO {
    private Integer idSale;

    @JsonIncludeProperties(value = {"idClient"}) //Asi solo cuando se haga una peticion de sales devuelva el json asociado pero con solo el campo de idClient
    private ClientDTO client;

    @JsonIncludeProperties(value = {"idUser","username"})
    private UserDTO user;
    private LocalDateTime dateTime;
    private Double total;
    private Double tax;
    private Boolean enabled;
    @JsonManagedReference
    private List<SaleDetailDTO> details;
}
