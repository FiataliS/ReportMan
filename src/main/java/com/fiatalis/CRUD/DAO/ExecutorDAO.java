package com.fiatalis.CRUD.DAO;

import com.fiatalis.CRUD.entytis.Entity;

import java.util.List;

public class ExecutorDAO implements DAO{

    @Override
    public Entity findById(Long id) {
        return null;
    }

    @Override
    public Entity findByName(String name) {
        return null;
    }

    @Override
    public List<Entity> findAll() {
        return null;
    }

    @Override
    public boolean saveOrUpdate(Entity entity) {
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }

    @Override
    public boolean deleteByName(String name) {
        return false;
    }
}
