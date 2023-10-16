package com.fiatalis.windows.components;

import javax.swing.*;
import java.awt.*;

public class TabbedPane extends JTabbedPane {
    public TabbedPane() {
        ScrollPane scrollPane = new ScrollPane();
        JPanel tablePanel = new JPanel(new BorderLayout());
        JTable table = new ListQuarterlyReports();
        scrollPane.add(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.add(table.getTableHeader(),BorderLayout.NORTH);

        this.add("Ежеквартальные отчеты", tablePanel);
        this.add("Ежемесячные отчеты", new ListMonthlyReports());
    }


}
