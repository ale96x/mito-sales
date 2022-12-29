package com.mitocode.service;

import com.mitocode.dto.IProcedureDTO;
import com.mitocode.dto.ProcedureDTO;
import com.mitocode.model.Sale;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public interface ISaleService extends ICRUD<Sale,Integer>{
    //Llamada a store procedure(function)
    List<ProcedureDTO> callProcedure(); //el procedure devuelve un List<Object[]> Para devolver una lista List<ProcedureDTO> debemos hacer el casteo en service

    List<ProcedureDTO> callProcedure2();

    List<IProcedureDTO> callProcedure3();

    List<IProcedureDTO> callProcedure4(@Param("p_id_client") Integer id);

    Sale getMostExpensive();

    String getBestSellerPerson(); //El mejor vendedor

    Map<String, Long> getSalesCountBySeller(); //Obtener cantidad de ventas por vendedor

    Map<String,Double> getMostSellerProduct(); //Obtener el producto que mas cantidad se vendio, como no tenemos un repositorio en sale_detail usaremos lo que tenemos
                                                //Solo en SaleDeatil hay los detalles de los productos
}
