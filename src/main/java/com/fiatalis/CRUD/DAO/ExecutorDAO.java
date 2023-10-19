package com.fiatalis.CRUD.DAO;

import com.fiatalis.CRUD.ConnectDataBaseUtils;
import com.fiatalis.CRUD.entytis.Entity;
import com.fiatalis.CRUD.entytis.Executor;
import com.fiatalis.windows.modelTable.SecondModel;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
            executor.setName(rs.getString(2));
            executor.setResponsible(rs.getString(3));
            executor.setPhone(rs.getString(4));
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
            executor.setName(rs.getString(2));
            executor.setResponsible(rs.getString(3));
            executor.setPhone(rs.getString(4));
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
            Executor executor = new Executor();
            executor.setId((long) rs.getInt(1));
            executor.setName(rs.getString(3));
            executor.setResponsible(rs.getString(4));
            executor.setPhone(rs.getString(5));
            list.add(executor);
        }
        return list;
    }

    @SneakyThrows
    @Override
    public boolean saveOrUpdate(Entity entity) {
        Executor executor = (Executor) entity;
        Executor r = (Executor) findByName(executor.getName());
        if (r != null) {
            executor.setId(r.getId());
        }
        try {
            if (executor.getId() == -1) {
                int x = statement.executeUpdate("insert into executor\n" +
                        " (name, date, frequency, submitted)\n" +
                        "values ('"
                        + executor.getName() + "', '"
                        + executor.getResponsible() + "', '"
                        + executor.getPhone() + "');");
                return x == 1 ? true : false;
            } else {
                int x = statement.executeUpdate("update executor set " +
                        "name= '" + executor.getName() + "', " +
                        "responsible= '" + executor.getResponsible() + "', " +
                        "phone= '" + executor.getPhone() + "', " +
                        "WHERE id= " + executor.getId() + ";");
                return x == 1 ? true : false;
            }
        } catch (Exception e) {
            return false;
        }
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
}
