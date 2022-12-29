package com.mitocode.service;

import com.mitocode.model.Category;
import com.mitocode.repo.ICategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ICategoryService extends ICRUD<Category,Integer>{
    List<Category> findByName(String name);

    List<Category> findByNameLike(String name);

    List<Category> findByNameContains(String name);

    List<Category> findByNameStartsWith(String name);

    List<Category> findByNameAndEnabled(String name, Boolean enabled);

    List<Category> findByNameOrEnabled(String name, Boolean enabled);

    List<Category> findByEnabledTrue();

    Category findOneByName(String name);

    List<Category> getByNameAndDescription2(String x,String y);

    List<Category> getByNameAndDescription3();

    Integer updateNameById(Integer id,String name);

    Page<Category> findPage(Pageable pageable);

    List<Category> findAllOrder(String param);
}
