package com.fiatalis.CRUD.DAO;

import com.fiatalis.CRUD.ConnectDataBaseUtils;
import com.fiatalis.CRUD.Frequency;
import com.fiatalis.entytis.Entity;
import com.fiatalis.entytis.Executor;
import com.fiatalis.entytis.Report;
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
            Report report = new Report();
            report.setId((long) rs.getInt(1));
            if (!rs.getString(2).equals("null")) report.setName(rs.getString(2));
            if (!rs.getString(3).equals("null")) report.setDate(Date.valueOf(rs.getString(3)));
            report.setFrequency(Frequency.valueOf(rs.getString(4)));
            report.setSubmitted(Boolean.valueOf(rs.getString(5)));
            report.setLink(rs.getString(6));
            report.setHistory(Boolean.valueOf(rs.getString(7)));
            return report;
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
            Report report = new Report();
            report.setId((long) rs.getInt(1));
            if (!rs.getString(2).equals("null")) report.setName(rs.getString(2));
            if (!rs.getString(3).equals("null")) report.setDate(Date.valueOf(rs.getString(3)));
            report.setFrequency(Frequency.valueOf(rs.getString(4)));
            report.setSubmitted(Boolean.valueOf(rs.getString(5)));
            report.setLink(rs.getString(6));
            report.setHistory(Boolean.valueOf(rs.getString(7)));
            return report;
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
            Report report = new Report();
            report.setId((long) rs.getInt(1));
            if (!rs.getString(2).equals("null")) report.setName(rs.getString(2));
            if (!rs.getString(3).equals("null")) report.setDate(Date.valueOf(rs.getString(3)));
            report.setFrequency(Frequency.valueOf(rs.getString(4)));
            report.setSubmitted(Boolean.valueOf(rs.getString(5)));
            report.setLink(rs.getString(6));
            report.setHistory(Boolean.valueOf(rs.getString(7)));
            list.add(report);
        }
        return list;
    }

    @SneakyThrows
    @Override
    public boolean saveOrUpdate(Entity entity) {
        Report report = (Report) entity;
        Report r = (Report) findByName(report.getName());
        if (r != null) report.setId(r.getId());
        if (report.getId() == -1) {
            return save(report);
        } else {
            return update(report);
        }
    }

    @SneakyThrows
    private boolean save(Report report) {
        int x = statement.executeUpdate("insert into report\n" +
                " (name, date, frequency, submitted, link, history)\n" +
                "values ('"
                + report.getName() + "', '"
                + report.getDate() + "', '"
                + report.getFrequency() + "', '"
                + report.getSubmitted() + "', '"
                + report.getLink() + "', '"
                + report.getHistory() + "');");
        return x == 1 ? true : false;
    }

    @SneakyThrows
    private boolean update(Report report) {
        int x = statement.executeUpdate("update report set " +
                "name= '" + report.getName() + "', " +
                "date= '" + report.getDate() + "', " +
                "frequency= '" + report.getFrequency() + "', " +
                "submitted= '" + report.getSubmitted() + "', " +
                "link= '" + report.getLink() + "', " +
                "history= '" + report.getHistory() + "' " +
                "WHERE id= " + report.getId() + ";");
        return x == 1 ? true : false;
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
        Report report = (Report) findByName(name);
        deleteExecutorByReport(report.getId());
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
            Report report = (Report) entity;
            if (report.getName() == null) {
                deleteById(report.getId());
            }
        }
    }
}
