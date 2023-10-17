package com.fiatalis.windows.components.buttons;

import com.fiatalis.CRUD.Frequency;
import com.fiatalis.CRUD.entytis.Reports;
import com.fiatalis.windows.components.ListReports;
import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.Date;

public class ButtonAdd extends Buttons {

    public ButtonAdd() {
        this.setText("Новая задача");
        listener();
    }

    @Override
    public void listener() {
        this.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListReports.getInstance().addRow(new Reports("Отчет 1"+  Math.random(), new Date(123), Frequency.Monthly, false));
            }
        });
    }
}
