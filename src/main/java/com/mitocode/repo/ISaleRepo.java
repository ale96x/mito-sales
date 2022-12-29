package com.mitocode.repo;

import com.mitocode.dto.IProcedureDTO;
import com.mitocode.dto.ProcedureDTO;
import com.mitocode.model.Sale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ISaleRepo extends IGenericRepo<Sale,Integer> {
    @Query(value = "select * from fn_sales();",nativeQuery = true)
    List<Object[]> callProcedure();

    @Query(nativeQuery = true,name = "Sale.fn_sales")
    List<ProcedureDTO> callProcedure2();

    @Procedure(procedureName = "fn_sales")
    List<IProcedureDTO> callProcedure3();

    @Procedure(procedureName = "fn_salesParameter")
    List<IProcedureDTO> callProcedure4(@Param("p_id_client") Integer id);
}
