package com.fiatalis.CRUD.DAO;

import com.fiatalis.CRUD.entytis.Entity;

import java.util.List;

public interface DAO {
    Entity findById(Long id);
    Entity findByName(String name);
    List<Entity> findAll();
    boolean saveOrUpdate(Entity entity);
    boolean deleteById(Long id);
    boolean deleteByName(String name);
}
