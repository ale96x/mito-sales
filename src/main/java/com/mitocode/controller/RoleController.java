package com.mitocode.controller;

import com.mitocode.dto.RoleDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Role;
import com.mitocode.service.IRoleService;
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
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private IRoleService service;

    @Autowired
    @Qualifier("roleMapper")
    ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<RoleDTO>> redAll() throws Exception{
        List<RoleDTO> list = service.readAll()
                .stream()
                .map(rol -> mapper.map(rol, RoleDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoleDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Role obj = service.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id); //Se envia la excepcion pero GlobalErrorHandler lo maneja para enviar una respuesta personalizada
        }
        return new ResponseEntity<>(mapper.map(obj,RoleDTO.class),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RoleDTO> create(@Valid @RequestBody RoleDTO roleDTO) throws  Exception{
        Role obj = service.create(mapper.map(roleDTO, Role.class));
        return new ResponseEntity<>(mapper.map(obj, RoleDTO.class),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<RoleDTO> update(@Valid @RequestBody RoleDTO dto) throws Exception{
        Role obj = service.readById(dto.getIdRole());
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + dto.getIdRole());
        }
        Role rol = service.update(mapper.map(dto, Role.class));
        return new ResponseEntity<>(mapper.map(rol,RoleDTO.class),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Role obj = service.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

