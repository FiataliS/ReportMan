package com.fiatalis;

import com.fiatalis.CRUD.ConnectDataBaseUtils;
import com.fiatalis.CRUD.DAO.ReportsDAOImpl;
import com.fiatalis.CRUD.Frequency;
import com.fiatalis.CRUD.entytis.Reports;
import com.fiatalis.windows.Controller;

import javax.swing.*;
import java.sql.Date;

public class Main {
    public static void main(String[] args) throws Exception {
        ConnectDataBaseUtils.getInstance().connect();
        ReportsDAOImpl rp = new ReportsDAOImpl(ConnectDataBaseUtils.getInstance().getStmt());
        rp.saveOrUpdate(new Reports("Отчет 11", new Date(1), Frequency.Quarterly, true));
        JFrame.setDefaultLookAndFeelDecorated(true);
        javax.swing.SwingUtilities.invokeLater(() -> Controller.startWindows());
    }


    private static void addSint(ReportsDAOImpl rp) {
        for (int i = 0; i < 100; i++) {
            rp.saveOrUpdate(new Reports("Отчет " + i, new Date(1 + i), i % 2 == 0 ? Frequency.Quarterly : Frequency.Monthly, true));
        }
    }

}