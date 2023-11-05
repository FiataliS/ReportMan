package com.fiatalis.windows.components;

import com.fiatalis.windows.MainTable;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
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
        super();
        this.setBorder(new BevelBorder(0));
        Image img = Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("com.fiatalis/image/buttonDelete.png"));
        this.setIcon(new ImageIcon(img));
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
