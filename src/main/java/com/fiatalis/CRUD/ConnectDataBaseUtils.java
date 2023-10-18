package com.fiatalis.CRUD;

import lombok.Data;
import lombok.SneakyThrows;

import java.io.File;
import java.sql.*;

@Data
public class ConnectDataBaseUtils {
    private Connection connection;
    private Statement stmt;
    private static volatile ConnectDataBaseUtils instance;

    public static ConnectDataBaseUtils getInstance() {
        ConnectDataBaseUtils localInstance = instance;
        if (localInstance == null) {
            synchronized (ConnectDataBaseUtils.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ConnectDataBaseUtils();
                }
            }
        }
        return localInstance;
    }

    @SneakyThrows
    public void connect() {
        createFolder("date");
        String fileName = "date/report.db";
        Boolean check = checkFileIni(fileName);
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:" + fileName);
        stmt = connection.createStatement();
        if (!check) createTable();
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public void createTable() {
        stmt.execute("CREATE TABLE reportMain (\n" +
                "    id        INTEGER     PRIMARY KEY AUTOINCREMENT\n" +
                "                          NOT NULL,\n" +
                "    name      TEXT        NOT NULL\n" +
                "                          UNIQUE,\n" +
                "    date      DATE        NOT NULL,\n" +
                "    frequency TEXT        NOT NULL,\n" +
                "    submitted TEXT (5, 5) NOT NULL\n" +
                ");");
        stmt.execute("CREATE TABLE reportHistory (\n" +
                "    id        INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                      NOT NULL,\n" +
                "    name      TEXT    NOT NULL,\n" +
                "    date      TEXT    NOT NULL,\n" +
                "    frequency TEXT    NOT NULL\n" +
                ");");
    }

    private boolean checkFileIni(String fileCheck) {
        File file = new File(fileCheck);
        if (!file.exists()) {
            return false;
        }
        return true;
    }

    private void createFolder(String folderName){
        File folder = new File(folderName);
        if (!folder.exists()) folder.mkdir();
    }
}
