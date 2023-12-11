package com.fiatalis.windows.components.up;

import com.fiatalis.windows.components.center.Table;

import javax.swing.*;
import java.awt.*;

public class ButtonDelete extends ButtonMenuItem {

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
        super("buttonDelete.png", "Удалить");
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
        int choice = JOptionPane.showConfirmDialog(null, "Удаление будет не обратимо после сохранения!", "Внимание!", JOptionPane.OK_CANCEL_OPTION);
        if (choice == JOptionPane.OK_OPTION) {
            Table.getInstance().deleteRowEntity();
        }
    }
}
