package com.mitocode.controller;

import com.mitocode.dto.UserDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.User;
import com.mitocode.service.IUserService;
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
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserService service;

    @Autowired
    @Qualifier("userMapper")
    ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<UserDTO>> redAll() throws Exception{
        List<UserDTO> list = service.readAll()
                .stream()
                .map(user -> mapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> readById(@PathVariable("id") Integer id) throws Exception{
        User obj = service.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id); //Se envia la excepcion pero GlobalErrorHandler lo maneja para enviar una respuesta personalizada
        }
        return new ResponseEntity<>(mapper.map(obj,UserDTO.class),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO categoryDTO) throws  Exception{
        User obj = service.create(mapper.map(categoryDTO, User.class));
        return new ResponseEntity<>(mapper.map(obj, UserDTO.class),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<UserDTO> update(@Valid @RequestBody UserDTO dto) throws Exception{
        User obj = service.readById(dto.getIdUser());
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + dto.getIdUser());
        }
        User user = service.update(mapper.map(dto, User.class));
        return new ResponseEntity<>(mapper.map(user,UserDTO.class),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        User obj = service.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

