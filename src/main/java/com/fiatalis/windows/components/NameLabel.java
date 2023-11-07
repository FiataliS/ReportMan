package com.fiatalis.windows.components;

import javax.swing.*;

public class NameLabel extends JLabel {

    private static volatile NameLabel instance;

    public static NameLabel getInstance() {
        NameLabel localInstance = instance;
        if (localInstance == null) {
            synchronized (NameLabel.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new NameLabel();
                }
            }
        }
        return localInstance;
    }

    public NameLabel() {
        this.setVisible(false);
    }
}
