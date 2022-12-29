package com.mitocode.controller;

import com.mitocode.exception.ModelNotFoundException;
import com.mitocode.dto.CategoryDTO;
import com.mitocode.model.Category;
import com.mitocode.service.ICategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService service;

    @Autowired
    @Qualifier("categoryMapper")
    ModelMapper mapper;


    //@PreAuthorize("@authService.checkRole('readAll')")
    @PreAuthorize("hasAuthority('ADMIN')") //Se determina que solo los usuarios con rol ADMIN podran realizar la peticion a este EndPoint
    @GetMapping
    public ResponseEntity<List<CategoryDTO>> redAll() throws Exception{
        List<CategoryDTO> list = service.readAll()
                .stream()
                .map(cat -> mapper.map(cat, CategoryDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> readById(@PathVariable("id") Integer id) throws Exception{
        Category obj = service.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id); //Se envia la excepcion pero GlobalErrorHandler lo maneja para enviar una respuesta personalizada
        }
        return new ResponseEntity<>(mapper.map(obj,CategoryDTO.class),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> create(@Valid @RequestBody CategoryDTO categoryDTO) throws  Exception{
        Category obj = service.create(mapper.map(categoryDTO, Category.class));
        return new ResponseEntity<>(mapper.map(obj, CategoryDTO.class),HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CategoryDTO> update(@Valid @RequestBody CategoryDTO dto) throws Exception{
        Category obj = service.readById(dto.getId());
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + dto.getId());
        }
        Category cat = service.update(mapper.map(dto, Category.class));
        return new ResponseEntity<>(mapper.map(cat,CategoryDTO.class),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception{
        Category obj = service.readById(id);
        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    ///////Queries///////
    @GetMapping("/find/name/{name}")
    public ResponseEntity<List<CategoryDTO>> findAllbyName(@PathVariable("name") String name){
        List<CategoryDTO> list = service.findByNameContains(name).stream().map(cat -> mapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }

    @GetMapping("/starts/name/{name}")
    public ResponseEntity<List<CategoryDTO>> findByNameStartsWith(@PathVariable("name") String name){
        List<CategoryDTO> list = service.findByNameStartsWith(name).stream().map(cat -> mapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }

    @GetMapping("/find")
    public ResponseEntity<List<CategoryDTO>> findByNameAndEnabled(@RequestParam("name") String name, @RequestParam("enabled") Boolean enabled){
        List<CategoryDTO> list = service.findByNameAndEnabled(name,enabled).stream().map(cat -> mapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }

    @GetMapping("/find/or")
    public ResponseEntity<List<CategoryDTO>> findByNameOrEnabled(@RequestParam("name") String name, @RequestParam("enabled") Boolean enabled){
        List<CategoryDTO> list = service.findByNameOrEnabled(name,enabled).stream().map(cat -> mapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }

    @GetMapping("/find/enabled/true")
    public ResponseEntity<List<CategoryDTO>> findByEnabledTrue(){
        List<CategoryDTO> list = service.findByEnabledTrue().stream().map(cat -> mapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }

    @GetMapping("/find/one/{name}")
    public ResponseEntity<CategoryDTO> readByOneByName(@PathVariable("name") String name) throws Exception{
        Category obj = service.findOneByName(name);
        return new ResponseEntity<>(mapper.map(obj,CategoryDTO.class),HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<CategoryDTO>> getByNameAndDescription2(@RequestParam("name") String name, @RequestParam("description") String description) throws Exception{
        List<CategoryDTO> list = service.getByNameAndDescription2(name,description).stream().map(cat -> mapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/name/description")
    public ResponseEntity<List<CategoryDTO>> getByNameAndDescription3() throws Exception{
        List<CategoryDTO> list = service.getByNameAndDescription3().stream().map(cat -> mapper.map(cat, CategoryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.ACCEPTED);
    }

    @PutMapping("/update")
    public ResponseEntity<Integer> updateNameById(@RequestParam("name") String name, @RequestParam("id") Integer id) throws Exception{
        Integer cantUpdate = service.updateNameById(id,name);
        return new ResponseEntity<>(cantUpdate, HttpStatus.ACCEPTED);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<Category>> findPage(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size){
        PageRequest pageRequest = PageRequest.of(page,size);
        Page<Category> pageResponse = service.findPage(pageRequest);
        return new ResponseEntity<>(pageResponse,HttpStatus.OK);
    }

    @GetMapping("/order")
    public ResponseEntity<List<Category>> findPage(@RequestParam(name = "param", defaultValue = "ASC") String param){
        List<Category> lst = service.findAllOrder(param);
        return new ResponseEntity<>(lst,HttpStatus.OK);
    }
}

