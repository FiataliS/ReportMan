package com.fiatalis.CRUD.DAO;

import com.fiatalis.CRUD.ConnectDataBaseUtils;
import com.fiatalis.entytis.Entity;
import com.fiatalis.entytis.Executor;
import com.fiatalis.entytis.Reports;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExecutorDAO implements DAO {
    private Statement statement;

    public ExecutorDAO() {
        this.statement = ConnectDataBaseUtils.getInstance().getStmt();
    }

    @SneakyThrows
    @Override
    public Executor findById(Long id) {
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM executor WHERE ID LIKE " + id + ";");
            Executor executor = new Executor();
            executor.setId((long) rs.getInt(1));
            executor.setIdReport((long) rs.getInt(2));
            executor.setName(rs.getString(3));
            executor.setResponsible(rs.getString(4));
            executor.setPhone(rs.getString(5));
            executor.setSubmit(Boolean.valueOf(rs.getString(6)));
            executor.setHistory(Boolean.valueOf(rs.getString(7)));
            return executor;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @SneakyThrows
    @Override
    public Entity findByName(String name) {
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM executor WHERE name LIKE '" + name + "';");
            if (rs == null || rs.getString(1).equals("id")) return null;
            Executor executor = new Executor();
            executor.setId((long) rs.getInt(1));
            executor.setIdReport((long) rs.getInt(2));
            executor.setName(rs.getString(3));
            executor.setResponsible(rs.getString(4));
            executor.setPhone(rs.getString(5));
            executor.setSubmit(Boolean.valueOf(rs.getString(6)));
            executor.setHistory(Boolean.valueOf(rs.getString(7)));
            return executor;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @SneakyThrows
    @Override
    public List<Entity> findAll(Long entityId) {
        ResultSet rs = statement.executeQuery("SELECT * FROM executor where id_report LIKE " + entityId + ";");
        List<Entity> list = new ArrayList<>();
        while (rs.next()) {
            if (rs.getString(2) == null) break;
            Executor executor = new Executor();
            executor.setId((long) rs.getInt(1));
            executor.setIdReport((long) rs.getInt(2));
            if (!rs.getString(3).equals("null")) executor.setName(rs.getString(3));
            if (!rs.getString(4).equals("null")) executor.setResponsible(rs.getString(4));
            if (!rs.getString(5).equals("null")) executor.setPhone(rs.getString(5));
            executor.setSubmit(Boolean.valueOf(rs.getString(6)));
            executor.setHistory(Boolean.valueOf(rs.getString(7)));
            list.add(executor);
        }
        return list;
    }

    @Override
    public boolean saveOrUpdate(Entity entity) {
        Executor executor = (Executor) entity;
        Executor r = (Executor) findByName(executor.getName());
        if (r != null && r.getIdReport() == executor.getIdReport()) executor.setId(r.getId());
        if (executor.getId() == -1) {
            return save(executor);
        } else {
            return update(executor);
        }
    }

    @SneakyThrows
    private boolean save(Executor executor) {
        int x = statement.executeUpdate("insert into executor\n" +
                " (id_report, name, responsible, phone, submit, history)\n" +
                "values ("
                + executor.getIdReport() + ", '"
                + executor.getName() + "', '"
                + executor.getResponsible() + "', '"
                + executor.getPhone() + "', '"
                + executor.getSubmit() + "', '"
                + executor.getHistory() + "');");
        return x == 1 ? true : false;
    }

    @SneakyThrows
    private boolean update(Executor executor) {
        int x = statement.executeUpdate("update executor set " +
                "name= '" + executor.getName() + "', " +
                "responsible= '" + executor.getResponsible() + "', " +
                "phone= '" + executor.getPhone() + "', " +
                "submit= '" + executor.getSubmit() + "', " +
                "history= '" + executor.getHistory() + "' " +
                "WHERE id= " + executor.getId() + ";");
        return x == 1 ? true : false;
    }

    @Override
    public boolean deleteById(Long id) {
        try {
            int x = statement.executeUpdate("delete from executor WHERE id = " + id + ";");
            return x == 1 ? true : false;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean deleteByName(String name) {
        try {
            int x = statement.executeUpdate("delete from executor WHERE name ='" + name + "';");
            return x == 1 ? true : false;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public void deleteNull() {
        ReportsDAO reportsDAO = new ReportsDAO();
        List<Entity> listReport = reportsDAO.findAll(null);
        List<Entity> list = new ArrayList<>();
        for (Entity entity : listReport) {
            Reports reports = (Reports) entity;
            list.addAll(findAll(reports.getId()));
        }
        for (Entity entity : list) {
            Executor executor = (Executor) entity;
            if (executor.getName() == null) {
                deleteById(executor.getId());
            }
        }
    }
}
