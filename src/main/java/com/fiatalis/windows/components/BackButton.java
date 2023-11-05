package com.fiatalis.windows.components;

import com.fiatalis.windows.MainTable;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BackButton extends JMenuItem {

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
        super();
        this.setBorder(new BevelBorder(0));
        Image img = Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("com.fiatalis/image/buttonBack.png"));
        this.setIcon(new ImageIcon(img));
        this.setHorizontalTextPosition(this.getSize().width / 2);
        this.setVisible(false);
        listeners();
    }

    private void listeners() {
        this.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainTable.getInstance().switchModel();
            }
        });
    }
}
