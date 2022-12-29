package com.mitocode.service.impl;

import com.mitocode.model.Category;
import com.mitocode.repo.ICategoryRepo;
import com.mitocode.repo.IGenericRepo;
import com.mitocode.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl extends CRUDImpl<Category,Integer> implements ICategoryService{

    @Autowired
    private ICategoryRepo repo;

    @Override
    protected IGenericRepo<Category, Integer> getRepo() {
        return this.repo;
    }

    @Override
    public List<Category> findByName(String name) {
        return repo.findByName(name);
    }

    @Override
    public List<Category> findByNameLike(String name) {
        return repo.findByNameLike("%" + name + "%");
    }

    @Override
    public List<Category> findByNameContains(String name) {
        return repo.findByNameContains(name);
    }

    @Override
    public List<Category> findByNameStartsWith(String name) {
        return repo.findByNameStartsWith(name);
    }

    @Override
    public List<Category> findByNameAndEnabled(String name, Boolean enabled) {
        return repo.findByNameAndEnabled(name,enabled);
    }

    @Override
    public List<Category> findByNameOrEnabled(String name, Boolean enabled) {
        return repo.findByNameOrEnabled(name,enabled);
    }

    @Override
    public List<Category> findByEnabledTrue() {
        return repo.findByEnabledTrue();
    }

    @Override
    public Category findOneByName(String name) {
        return repo.findOneByName(name);
    }

    @Override
    public List<Category> getByNameAndDescription2(String x, String y) {
        return repo.getByNameAndDescription2(x,y);
    }

    @Override
    public List<Category> getByNameAndDescription3() {
        return repo.getByNameAndDescription3();
    }

    @Override
    public Integer updateNameById(Integer id, String name) {
        return repo.updateNameById(id,name);
    }

    @Override
    public Page<Category> findPage(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public List<Category> findAllOrder(String param) {
        Sort.Direction direction = param.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC;
        return repo.findAll(Sort.by(direction,"name"));
    }
}
