package com.fiatalis.windows.components;

import com.fiatalis.windows.MainTable;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class DeleteButton extends JMenuItem {

    public DeleteButton() {
        super("Удалить отчет");
        listeners();
    }

    private void listeners() {
        this.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainTable.getInstance().deleteRowEntity();
            }
        });
    }
}
