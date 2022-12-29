package com.mitocode.controller;

import com.mitocode.dto.IProcedureDTO;
import com.mitocode.dto.ProcedureDTO;
import com.mitocode.dto.SaleDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Sale;
import com.mitocode.service.ISaleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sales")
public class SaleController {

    @Autowired
    private ISaleService service;

    @Autowired
    @Qualifier("saleMapper")
    ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<SaleDTO>> redAll() throws Exception{
        List<SaleDTO> list = service.readAll()
                .stream()
                .map(sale -> mapper.map(sale, SaleDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Sale obj = service.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id); //Se envia la excepcion pero GlobalErrorHandler lo maneja para enviar una respuesta personalizada
        }
        return new ResponseEntity<>(mapper.map(obj,SaleDTO.class),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SaleDTO> create(@Valid @RequestBody SaleDTO SaleDTO) throws  Exception{
        Sale obj = service.create(mapper.map(SaleDTO, Sale.class));
        return new ResponseEntity<>(mapper.map(obj, SaleDTO.class),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<SaleDTO> update(@Valid @RequestBody SaleDTO dto) throws Exception{
        Sale obj = service.readById(dto.getIdSale());
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + dto.getIdSale());
        }
        Sale sale = service.update(mapper.map(dto, Sale.class));
        return new ResponseEntity<>(mapper.map(sale,SaleDTO.class),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Sale obj = service.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/procedure") //Aqui no se utiliza el mapper porque no utilizamos DAO solo DTO asi se comunica la base de datos con el controller
    public ResponseEntity<List<ProcedureDTO>> callProcedure() throws Exception{
        return new ResponseEntity<>(service.callProcedure(),HttpStatus.OK);
    }

    @GetMapping("/procedure2") //Aqui no se utiliza el mapper porque no utilizamos DAO solo DTO asi se comunica la base de datos con el controller
    public ResponseEntity<List<ProcedureDTO>> callProcedure2() throws Exception{
        return new ResponseEntity<>(service.callProcedure2(),HttpStatus.OK);
    }

    @GetMapping("/procedure3") //Aqui no se utiliza el mapper porque no utilizamos DAO solo DTO asi se comunica la base de datos con el controller
    public ResponseEntity<List<IProcedureDTO>> callProcedure3() throws Exception{
        return new ResponseEntity<>(service.callProcedure3(),HttpStatus.OK);
    }

    @GetMapping("/procedure4/{id}") //Aqui no se utiliza el mapper porque no utilizamos DAO solo DTO asi se comunica la base de datos con el controller
    public ResponseEntity<List<IProcedureDTO>> callProcedure4(@PathVariable Integer id) throws Exception{
        return new ResponseEntity<>(service.callProcedure4(id),HttpStatus.OK);
    }

    @GetMapping("/mostexpensive") //Aqui no se utiliza el mapper porque no utilizamos DAO solo DTO asi se comunica la base de datos con el controller
    public ResponseEntity<SaleDTO> getMostExpensive() throws Exception{
        SaleDTO dto = mapper.map(service.getMostExpensive(), SaleDTO.class);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping("/bestseller") //Aqui no se utiliza el mapper porque no utilizamos DAO solo DTO asi se comunica la base de datos con el controller
    public ResponseEntity<String> getBestSeller() throws Exception{
        return new ResponseEntity<>(service.getBestSellerPerson(),HttpStatus.OK);
    }

    @GetMapping("/sellercount") //Aqui no se utiliza el mapper porque no utilizamos DAO solo DTO asi se comunica la base de datos con el controller
    public ResponseEntity<Map<String,Long>> getSalesCountBySeller() throws Exception{
        return new ResponseEntity<>(service.getSalesCountBySeller(),HttpStatus.OK);
    }

    @GetMapping("/bestproduct") //Aqui no se utiliza el mapper porque no utilizamos DAO solo DTO asi se comunica la base de datos con el controller
    public ResponseEntity<Map<String,Double>> getMostSellerProduct() throws Exception{
        return new ResponseEntity<>(service.getMostSellerProduct(),HttpStatus.OK);
    }
}
