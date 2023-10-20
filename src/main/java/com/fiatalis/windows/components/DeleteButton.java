package com.fiatalis.windows.components;

import com.fiatalis.windows.MainTable;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class DeleteButton extends JMenuItem {

    private static volatile DeleteButton instance;

    public static DeleteButton getInstance() {
        DeleteButton localInstance = instance;
        if (localInstance == null) {
            synchronized (DeleteButton.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new DeleteButton();
                }
            }
        }
        return localInstance;
    }

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
