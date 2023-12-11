package com.fiatalis.windows.components.up;

import com.fiatalis.windows.components.center.Table;

import javax.swing.*;
import java.awt.*;

public class ButtonToHistoryAndBack extends ButtonMenuItem {
    private static volatile ButtonToHistoryAndBack instance;

    public static ButtonToHistoryAndBack getInstance() {
        ButtonToHistoryAndBack localInstance = instance;
        if (localInstance == null) {
            synchronized (ButtonToHistoryAndBack.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ButtonToHistoryAndBack();
                }
            }
        }
        return localInstance;
    }

    public ButtonToHistoryAndBack() {
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
