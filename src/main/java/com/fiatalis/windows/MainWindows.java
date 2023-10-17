package com.fiatalis.windows;

import com.fiatalis.windows.components.ListReports;
import com.fiatalis.windows.components.MenuBar;

import javax.swing.*;
import java.awt.*;

public class MainWindows extends JFrame {
    public MainWindows() throws HeadlessException {
        addComponent();
        this.setTitle("ReportMan");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(740, 480));
        this.setLocationRelativeTo(null);
        Toolkit.getDefaultToolkit().setDynamicLayout(false);
        this.setResizable(true);
    }

    private void addComponent() {
        this.add(new MenuBar(), BorderLayout.NORTH);
        this.add(new JScrollPane(ListReports.getInstance()), BorderLayout.CENTER);
    }
}
