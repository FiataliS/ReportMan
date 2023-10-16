package com.fiatalis.CRUD;

import lombok.Data;

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

    public void connect() throws Exception {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:reportMan.db");
        stmt = connection.createStatement();
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
