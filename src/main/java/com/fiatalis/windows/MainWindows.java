package com.fiatalis.windows;

import com.fiatalis.CRUD.ConnectDataBaseUtils;
import com.fiatalis.CRUD.DAO.ExecutorDAO;
import com.fiatalis.CRUD.DAO.ReportsDAO;
import com.fiatalis.utils.MessageUtils;
import com.fiatalis.windows.components.center.Table;
import com.fiatalis.windows.components.up.MenuBar;
import com.fiatalis.windows.components.up.ButtonSave;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindows extends JFrame {
    public MainWindows() {
        this.setUndecorated(false);
        addComponent();
        this.setTitle("ReportMan");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("com.fiatalis/image/icon.png")));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                boolean choice = true;
                if (ButtonSave.getInstance().isVisible())
                    choice = MessageUtils.alertChoice("Внимание!", "Несохраненные данные будут утеряны.");
                if (choice) {
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
        this.getContentPane().add(new MenuBar(), BorderLayout.NORTH);
        this.getContentPane().add(new JScrollPane(Table.getInstance()), BorderLayout.CENTER);
    }
}
