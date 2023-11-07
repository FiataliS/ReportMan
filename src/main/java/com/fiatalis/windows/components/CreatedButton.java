package com.fiatalis.windows.components;

import com.fiatalis.windows.MainTable;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CreatedButton extends JMenuItem {

    private static volatile CreatedButton instance;

    public static CreatedButton getInstance() {
        CreatedButton localInstance = instance;
        if (localInstance == null) {
            synchronized (CreatedButton.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CreatedButton();
                }
            }
        }
        return localInstance;
    }

    public CreatedButton() {
        super();
        this.setBorder(new BevelBorder(0));
        Image img = Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("com.fiatalis/image/buttonCreate.png"));
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(new ImageIcon(img)), BorderLayout.CENTER);
        this.add(panel);
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
