package com.fiatalis.windows;

import com.fiatalis.CRUD.ConnectDataBaseUtils;
import com.fiatalis.CRUD.DAO.ExecutorDAO;
import com.fiatalis.CRUD.DAO.ReportsDAO;
import com.fiatalis.windows.components.BackButton;
import com.fiatalis.windows.components.MenuBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindows extends JFrame {
    public MainWindows() throws HeadlessException {
        addComponent();
        this.setTitle("ReportMan");
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(MainWindows.this, "Не сохраненные данные будут потеряны!") == JOptionPane.OK_OPTION) {
                    new ExecutorDAO().deleteNull();
                    new ReportsDAO().deleteNull();
                    ConnectDataBaseUtils.getInstance().disconnect();
                    System.exit(0);
                }
            }
        });
        this.setMinimumSize(new Dimension(740, 480));
        this.setLocationRelativeTo(null);
        Toolkit.getDefaultToolkit().setDynamicLayout(false);
        this.setResizable(true);
    }

    private void addComponent() {
        this.add(new MenuBar(), BorderLayout.NORTH);
        this.add(new JScrollPane(MainTable.getInstance()), BorderLayout.CENTER);
        this.add(BackButton.getInstance(), BorderLayout.SOUTH);
    }
}
