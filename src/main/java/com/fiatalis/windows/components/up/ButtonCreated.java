package com.fiatalis.windows.components.up;

import com.fiatalis.windows.components.center.Table;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ButtonCreated extends JMenuItem {

    private static volatile ButtonCreated instance;

    public static ButtonCreated getInstance() {
        ButtonCreated localInstance = instance;
        if (localInstance == null) {
            synchronized (ButtonCreated.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ButtonCreated();
                }
            }
        }
        return localInstance;
    }

    public ButtonCreated() {
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
                Table.getInstance().addRowEntity();
            }
        });
    }
}
