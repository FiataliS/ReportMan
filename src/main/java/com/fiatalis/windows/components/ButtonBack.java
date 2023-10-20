package com.fiatalis.windows.components;

import com.fiatalis.windows.MainTable;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ButtonBack extends JButton {
    private static volatile ButtonBack instance;

    public static ButtonBack getInstance() {
        ButtonBack localInstance = instance;
        if (localInstance == null) {
            synchronized (ButtonBack.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ButtonBack();
                }
            }
        }
        return localInstance;
    }

    public ButtonBack() {
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
