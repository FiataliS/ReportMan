package com.fiatalis.CRUD.DAO;

import com.fiatalis.CRUD.entytis.Reports;

import java.util.List;

public interface ReportsDAO {
    Reports findById(Long id);

    Reports findByName(String name);

    List<Reports> findAll();

    boolean saveOrUpdate(Reports reports);

}
