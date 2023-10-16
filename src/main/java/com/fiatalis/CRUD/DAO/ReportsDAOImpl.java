package com.fiatalis.CRUD.DAO;

import com.fiatalis.CRUD.Frequency;
import com.fiatalis.CRUD.entytis.Reports;
import lombok.SneakyThrows;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReportsDAOImpl implements ReportsDAO {
    private Statement statement;

    public ReportsDAOImpl(Statement statement) {
        this.statement = statement;
    }

    @SneakyThrows
    @Override
    public Reports findById(Long id) {
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM reportMain WHERE ID LIKE " + id + ";");
            Reports reports = new Reports();
            reports.setId((long) rs.getInt(1));
            reports.setName(rs.getString(2));
            reports.setDate(Date.valueOf(rs.getString(3)));
            reports.setFrequency(Frequency.valueOf(rs.getString(4)));
            reports.setSubmitted(rs.getBoolean(5));
            return reports;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @SneakyThrows
    @Override
    public Reports findByName(String name) {
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM reportMain WHERE name LIKE '" + name + "';");
            Reports reports = new Reports();
            reports.setId((long) rs.getInt(1));
            reports.setName(rs.getString(2));
            reports.setDate(Date.valueOf(rs.getString(3)));
            reports.setFrequency(Frequency.valueOf(rs.getString(4)));
            reports.setSubmitted(rs.getBoolean(5));
            return reports;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @SneakyThrows
    @Override
    public List<Reports> findAll() {
        ResultSet rs = statement.executeQuery("SELECT * FROM reportMain");
        List<Reports> list = new ArrayList<>();
        while (rs.next()) {
            Reports reports = new Reports();
            reports.setId((long) rs.getInt(1));
            reports.setName(rs.getString(2));
            reports.setDate(Date.valueOf(rs.getString(3)));
            reports.setFrequency(Frequency.valueOf(rs.getString(4)));
            reports.setSubmitted(rs.getBoolean(5));
            list.add(reports);
        }
        return list;
    }

    @SneakyThrows
    @Override
    public boolean saveOrUpdate(Reports reports) {
        Reports r = findByName(reports.getName());
        if (r != null) {
            reports.setId(r.getId());
        }
        try {
            if (reports.getId() == -1) {
                statement.executeUpdate("insert into reportMain\n" +
                        " (name, date, frequency, submitted)\n" +
                        "values ('"
                        + reports.getName() + "', '"
                        + reports.getDate() + "', '"
                        + reports.getFrequency() + "', '"
                        + reports.getSubmitted() + "');");
                return true;
            } else {
                statement.executeUpdate("update reportMain set " +
                        "name= '" + reports.getName() + "', " +
                        "date= '" + reports.getDate() + "', " +
                        "frequency= '" + reports.getFrequency() + "', " +
                        "submitted= '" + reports.getSubmitted() + "' " +
                        "WHERE id= " + reports.getId() + ";");
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
