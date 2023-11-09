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
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(new ImageIcon(img)), BorderLayout.CENTER);
        this.add(panel);
        this.setVisible(false);
        listeners();
    }

    private void listeners() {
        this.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // NameLabel.getInstance().setVisible(false);
                MainTable.getInstance().switchModel();
            }
        });
    }
}
