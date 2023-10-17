package com.fiatalis.windows.components.buttons;


import com.fiatalis.CRUD.DAO.ReportsDAO;
import com.fiatalis.CRUD.DAO.ReportsDAOImpl;
import com.fiatalis.windows.components.ListReports;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ButtonDelete extends Buttons {

    public ButtonDelete() {
        this.setText("Удалить задачу");
        listener();
    }

    @Override
    public void listener() {
        this.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ListReports.getInstance().deleteRow();
            }
        });
    }
}
