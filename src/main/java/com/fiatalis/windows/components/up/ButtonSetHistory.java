package com.fiatalis.windows.components.up;

import com.fiatalis.windows.components.center.Table;

import javax.swing.*;
import java.awt.*;

public class ButtonSetHistory extends ButtonMenuItem {
    private static volatile ButtonSetHistory instance;

    public static ButtonSetHistory getInstance() {
        ButtonSetHistory localInstance = instance;
        if (localInstance == null) {
            synchronized (ButtonSetHistory.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ButtonSetHistory();
                }
            }
        }
        return localInstance;
    }

    public ButtonSetHistory() {
        super("buttonComplete.png","Завершить" );
    }


    @Override
    protected void action() {
        Image img = Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("com.fiatalis/image/deleteRowFiled.png"));
        final JOptionPane pane = new JOptionPane("Строка не выбрана!", JOptionPane.WARNING_MESSAGE, JOptionPane.OK_OPTION, new ImageIcon(img), new String[]{"OK"});
        JDialog dialog = pane.createDialog(null, "Ошибка!");
        if (Table.getInstance().getSelectedRow() < 0) {
            dialog.setVisible(true);
            return;
        }

        //Table.getInstance().deleteRowEntity();
    }
}
