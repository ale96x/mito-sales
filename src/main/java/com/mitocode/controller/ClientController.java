package com.mitocode.controller;

import com.mitocode.dto.ClientDTO;
import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.model.Client;
import com.mitocode.service.IClientService;
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
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private IClientService service;

    @Autowired
    @Qualifier("clientMapper")
    ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> redAll() throws Exception{
        List<ClientDTO> list = service.readAll()
                .stream()
                .map(client -> mapper.map(client, ClientDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Client obj = service.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id); //Se envia la excepcion pero GlobalErrorHandler lo maneja para enviar una respuesta personalizada
        }
        return new ResponseEntity<>(mapper.map(obj,ClientDTO.class),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDTO> create(@Valid @RequestBody ClientDTO clientDTO) throws  Exception{
        Client obj = service.create(mapper.map(clientDTO, Client.class));
        return new ResponseEntity<>(mapper.map(obj, ClientDTO.class),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ClientDTO> update(@Valid @RequestBody ClientDTO dto) throws Exception{
        Client obj = service.readById(dto.getIdClient());
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + dto.getIdClient());
        }
        Client client = service.update(mapper.map(dto, Client.class));
        return new ResponseEntity<>(mapper.map(client,ClientDTO.class),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Client obj = service.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

