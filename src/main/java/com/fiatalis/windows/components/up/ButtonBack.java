package com.fiatalis.windows.components.up;

import com.fiatalis.windows.components.center.Table;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ButtonBack extends JMenuItem {

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
        super();
        this.setBorder(new BevelBorder(0));
        this.setToolTipText("Назад");
        Image img = Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("com.fiatalis/image/buttonBack.png"));
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(new ImageIcon(img)), BorderLayout.CENTER);
        this.add(panel);
        this.setVisible(false);
        listeners();
    }

    @Override
    public JToolTip createToolTip() {
        return new CustomJToolTip(this);
    }

    private void listeners() {
        this.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // NameLabel.getInstance().setVisible(false);
                Table.getInstance().switchModel();
            }
        });
    }
}
