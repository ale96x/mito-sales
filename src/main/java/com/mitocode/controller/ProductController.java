package com.mitocode.controller;

import com.mitocode.dto.ProductDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Product;
import com.mitocode.service.IProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService service;

    @Autowired
    @Qualifier("productMapper")
    //Como hay varios @Beans que devuelven un ModelMapper se debe especificar con @Qualifier
    private ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProductDTO>> redAll() throws Exception{
        List<ProductDTO> list = service.readAll()
                .stream()
                .map(product -> mapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Product obj = service.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id); //Se envia la excepcion pero GlobalErrorHandler lo maneja para enviar una respuesta personalizada
        }
        return new ResponseEntity<>(mapper.map(obj,ProductDTO.class),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDTO> create(@Valid @RequestBody ProductDTO productDTO) throws  Exception{
        Product obj = service.create(mapper.map(productDTO, Product.class));
        return new ResponseEntity<>(mapper.map(obj, ProductDTO.class),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ProductDTO> update(@Valid @RequestBody ProductDTO dto) throws Exception{
        Product obj = service.readById(dto.getIdProduct());
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + dto.getIdProduct());
        }
        Product product = service.update(mapper.map(dto, Product.class));
        return new ResponseEntity<>(mapper.map(product,ProductDTO.class),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Product obj = service.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

