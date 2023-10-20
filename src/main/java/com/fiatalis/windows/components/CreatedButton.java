package com.fiatalis.windows.components;

import com.fiatalis.windows.MainTable;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CreatedButton extends JMenuItem {
    public CreatedButton() {
        super("Новый отчет");
        listeners();
    }

    private void listeners() {
        this.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainTable.getInstance().addRowEntity();
            }
        });
    }
}
