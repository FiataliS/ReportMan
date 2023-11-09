package com.fiatalis.windows.components;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;

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
        this.setVisible(true);
        this.setBorder(new BevelBorder(1));
        Dimension newDim = new Dimension(625, 45);
        this.setMinimumSize(newDim);
        this.setPreferredSize(newDim);
        this.setMaximumSize(newDim);
        this.setSize(newDim);
        this.revalidate();
    }
}
