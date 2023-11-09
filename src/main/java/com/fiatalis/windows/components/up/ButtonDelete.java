package com.fiatalis.windows.components.up;

import com.fiatalis.windows.components.center.Table;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;


public class ButtonDelete extends JMenuItem {

    private static volatile ButtonDelete instance;

    public static ButtonDelete getInstance() {
        ButtonDelete localInstance = instance;
        if (localInstance == null) {
            synchronized (ButtonDelete.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ButtonDelete();
                }
            }
        }
        return localInstance;
    }

    public ButtonDelete() {
        super();
        this.setBorder(new BevelBorder(0));
        Image img = Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("com.fiatalis/image/buttonDelete.png"));
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(new ImageIcon(img)), BorderLayout.CENTER);
        this.add(panel);
        listeners();
    }

    private void listeners() {
        this.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Image img = Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("com.fiatalis/image/deleteRowFiled.png"));
                final JOptionPane pane = new JOptionPane("Строка не выбрана!", JOptionPane.WARNING_MESSAGE, JOptionPane.OK_OPTION, new ImageIcon(img), new String[]{"OK"});
                JDialog dialog = pane.createDialog(null, "Ошибка!");
                if (Table.getInstance().getSelectedRow() < 0) {
                    dialog.setVisible(true);
                    return;
                }
                Table.getInstance().deleteRowEntity();
            }
        });
    }
}
