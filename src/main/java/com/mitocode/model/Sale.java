package com.mitocode.model;

import com.mitocode.dto.IProcedureDTO;
import com.mitocode.dto.ProcedureDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "sale")

 /*Este se usa con callProcedure2()*/
@SqlResultSetMapping(
        name = "Procedure.ProcedureDTO",
        classes = @ConstructorResult(targetClass = ProcedureDTO.class,
                columns = {@ColumnResult(name = "quantityfn", type = Integer.class),
                        @ColumnResult(name = "datetimefn", type = String.class)}
        )
)

@NamedNativeQuery(
        name = "Sale.fn_sales",
        query = "select * from fn_sales()",
        resultSetMapping = "Procedure.ProcedureDTO"
)

/*Este se usa con callProcedure3() - Es mas corto pero se debe crear una interface de lo que devuelve*/
@NamedStoredProcedureQuery(
        name = "getFnSales",
        procedureName = "fn_sales",
        resultClasses = IProcedureDTO.class //Aqui se indica la interfaz a usar
)

//Procedimiento con parametros
@NamedStoredProcedureQuery(
        name = "getFnSales2",
        procedureName = "fn_salesParameter", //Debe coincidir con lo definido en Repo
        resultClasses = IProcedureDTO.class, //Aqui se indica la interfaz a usar
        parameters = {
                @StoredProcedureParameter(name = "p_id_client", type = Integer.class, mode = ParameterMode.IN)
                //@StoredProcedureParameter(name = "ABC", type = Void.class, mode = ParameterMode.REF_CURSOR),
                //@StoredProcedureParameter(name = "DEF", type = String.class, mode = ParameterMode.OUT)
        }
)

public class Sale {
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSale;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private Double total;

    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private Double tax;

    @Column(nullable = false)
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "fk_sale_user"))
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_client", nullable = false, foreignKey = @ForeignKey(name = "fk_sale_client"))
    private Client client;

    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, fetch = FetchType.EAGER) //orphanRemoval = true
    private List<SaleDetail> details;
}
