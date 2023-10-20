package com.fiatalis.windows.components;

import com.fiatalis.windows.MainTable;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BackButton extends JButton {
    private static volatile BackButton instance;

    public static BackButton getInstance() {
        BackButton localInstance = instance;
        if (localInstance == null) {
            synchronized (BackButton.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new BackButton();
                }
            }
        }
        return localInstance;
    }

    public BackButton() {
        super("Закрыть исполнителей");
        listener();
        setVisible(false);
    }

    private void listener() {
        this.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainTable.getInstance().switchModel();
            }
        });
    }
}
