package com.mitocode.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProviderDTO {
    private Integer idProvider;
    private String name;
    private String address;
    private Boolean enabled;
}
