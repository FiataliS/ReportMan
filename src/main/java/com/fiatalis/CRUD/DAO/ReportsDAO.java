package com.fiatalis.CRUD.DAO;

import com.fiatalis.CRUD.ConnectDataBaseUtils;
import com.fiatalis.CRUD.Frequency;
import com.fiatalis.CRUD.entytis.Entity;
import com.fiatalis.CRUD.entytis.Executor;
import com.fiatalis.CRUD.entytis.Reports;
import lombok.SneakyThrows;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReportsDAO implements DAO {
    private Statement statement;

    public ReportsDAO() {
        this.statement = ConnectDataBaseUtils.getInstance().getStmt();
    }

    @SneakyThrows
    @Override
    public Entity findById(Long id) {
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM report WHERE ID LIKE " + id + ";");
            Reports reports = new Reports();
            reports.setId((long) rs.getInt(1));
            if (!rs.getString(2).equals("null")) reports.setName(rs.getString(2));
            if (!rs.getString(3).equals("null")) reports.setDate(Date.valueOf(rs.getString(3)));
            if (!rs.getString(4).equals("null")) reports.setFrequency(Frequency.valueOf(rs.getString(4)));
            reports.setSubmitted(rs.getBoolean(5));
            return reports;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @SneakyThrows
    @Override
    public Entity findByName(String name) {
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM report WHERE name LIKE '" + name + "';");
            if (rs == null || rs.getString(1).equals("id")) return null;
            Reports reports = new Reports();
            reports.setId((long) rs.getInt(1));
            if (!rs.getString(2).equals("null")) reports.setName(rs.getString(2));
            if (!rs.getString(3).equals("null")) reports.setDate(Date.valueOf(rs.getString(3)));
            if (!rs.getString(4).equals("null")) reports.setFrequency(Frequency.valueOf(rs.getString(4)));
            reports.setSubmitted(Boolean.valueOf(rs.getBoolean(5)));
            return reports;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @SneakyThrows
    @Override
    public List<Entity> findAll(Long entityId) {
        ResultSet rs = statement.executeQuery("SELECT * FROM report");
        List<Entity> list = new ArrayList<>();
        while (rs.next()) {
            Reports reports = new Reports();
            reports.setId((long) rs.getInt(1));
            if (!rs.getString(2).equals("null")) reports.setName(rs.getString(2));
            if (!rs.getString(3).equals("null")) reports.setDate(Date.valueOf(rs.getString(3)));
            if (!rs.getString(4).equals("null")) reports.setFrequency(Frequency.valueOf(rs.getString(4)));
            reports.setSubmitted(Boolean.valueOf(rs.getString(5)));
            list.add(reports);
        }
        return list;
    }

    @SneakyThrows
    @Override
    public boolean saveOrUpdate(Entity entity) {
        Reports reports = (Reports) entity;
        Reports r = (Reports) findByName(reports.getName());
        if (r != null) reports.setId(r.getId());
        try {
            if (reports.getId() == -1) {
                int x = statement.executeUpdate("insert into report\n" +
                        " (name, date, frequency, submitted)\n" +
                        "values ('"
                        + reports.getName() + "', '"
                        + reports.getDate() + "', '"
                        + reports.getFrequency() + "', '"
                        + reports.getSubmitted() + "');");
                return x == 1 ? true : false;
            } else {
                int x = statement.executeUpdate("update report set " +
                        "name= '" + reports.getName() + "', " +
                        "date= '" + reports.getDate() + "', " +
                        "frequency= '" + reports.getFrequency() + "', " +
                        "submitted= '" + reports.getSubmitted() + "' " +
                        "WHERE id= " + reports.getId() + ";");
                return x == 1 ? true : false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteById(Long id) {
        deleteExecutorByReport(id);
        try {
            int x = statement.executeUpdate("delete from report WHERE id = " + id + ";");
            return x == 1 ? true : false;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean deleteByName(String name) {
        Reports reports = (Reports) findByName(name);
        deleteExecutorByReport(reports.getId());
        try {
            int x = statement.executeUpdate("delete from report WHERE name ='" + name + "';");
            return x == 1 ? true : false;
        } catch (SQLException e) {
            return false;
        }
    }

    private void deleteExecutorByReport(Long reportID) {
        ExecutorDAO executorDAO = new ExecutorDAO();
        List<Entity> list = executorDAO.findAll(reportID);
        for (Entity entity : list) {
            Executor executor = (Executor) entity;
            executorDAO.deleteById(executor.getId());
        }
    }


    @Override
    public void deleteNull() {
        List<Entity> list = findAll(null);
        for (Entity entity : list) {
            Reports reports = (Reports) entity;
            if (reports.getName() == null) {
                deleteById(reports.getId());
            }
        }
    }
}
