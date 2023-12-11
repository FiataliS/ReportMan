package com.fiatalis.windows.components.up;

import com.fiatalis.utils.MessageUtils;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

abstract class ButtonMenuItem extends JMenuItem {
    protected String icon;
    protected String info;

    public ButtonMenuItem(String icon, String info) {
        this.icon = icon;
        this.info = info;
        setting();
        listeners();
        setVisible(false);
    }

    protected abstract void action();

    protected void setting() {
        this.setBorder(new BevelBorder(0));
        this.setToolTipText(info);
        this.setHorizontalTextPosition(SwingConstants.RIGHT);
        setIcon();
        this.revalidate();
    }

    private void setIcon() {
        Image img;
        img = Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("com.fiatalis/image/" + icon));
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(new ImageIcon(img)), BorderLayout.CENTER);
        this.removeAll();
        this.add(panel);
    }

    private void listeners() {
        this.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action();
            }
        });
    }

    @Override
    public JToolTip createToolTip() {
        return new CustomJToolTip(this);
    }

    public void alert(String title, String message) {
        MessageUtils.alert(title, message, "deleteRowFiled.png", JOptionPane.WARNING_MESSAGE, JOptionPane.OK_OPTION);
    }
}
