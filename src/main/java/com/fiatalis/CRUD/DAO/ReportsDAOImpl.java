package com.fiatalis.CRUD.DAO;

import com.fiatalis.CRUD.ConnectDataBaseUtils;
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

    public ReportsDAOImpl() {
        this.statement = ConnectDataBaseUtils.getInstance().getStmt();
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
            if (rs == null || rs.getString(0).equals("Id")) return null;
            Reports reports = new Reports();
            reports.setId((long) rs.getInt(1));
            reports.setName(rs.getString(2));
            reports.setDate(Date.valueOf(rs.getString(3)));
            reports.setFrequency(Frequency.valueOf(rs.getString(4)));
            reports.setSubmitted(Boolean.valueOf(rs.getBoolean(4)));
            return reports;
        } catch (SQLException e) {
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
            reports.setSubmitted(Boolean.valueOf(rs.getString(5)));
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
                int x = statement.executeUpdate("insert into reportMain\n" +
                        " (name, date, frequency, submitted)\n" +
                        "values ('"
                        + reports.getName() + "', '"
                        + reports.getDate() + "', '"
                        + reports.getFrequency() + "', '"
                        + reports.getSubmitted() + "');");
                return x == 1 ? true : false;
            } else {
                int x = statement.executeUpdate("update reportMain set " +
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
        try {
            int x = statement.executeUpdate("delete from reportMain WHERE id = " + id + ";");
            return x == 1 ? true : false;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean deleteByName(String name) {
        try {
            int x = statement.executeUpdate("delete from reportMain WHERE name ='" + name + "';");
            return x == 1 ? true : false;
        } catch (SQLException e) {
            return false;
        }
    }
}
