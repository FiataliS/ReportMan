package com.fiatalis.windows.components;

import com.fiatalis.CRUD.Frequency;
import com.fiatalis.CRUD.entytis.Reports;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.Date;

public class MenuFile extends JMenu {
    JMenuItem created = new JMenuItem("Новый отчет");
    JMenuItem delete = new JMenuItem("Удалить отчет");

    public MenuFile() {
        super("Отчет");
        this.add(created);
        this.add(new JPopupMenu.Separator());
        this.add(delete);
        listeners();
    }

    private void listeners() {
        delete.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListReports.getInstance().deleteRow();
            }
        });
        created.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListReports.getInstance().addRow(new Reports("Отчет 1" + Math.random(), new Date(123), Frequency.Monthly, false));
            }
        });
    }
}
