package com.fiatalis.windows.components.up;

import com.fiatalis.windows.components.center.Table;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ButtonOpenFile extends ButtonMenuItem {
    private static volatile ButtonOpenFile instance;

    public static ButtonOpenFile getInstance() {
        ButtonOpenFile localInstance = instance;
        if (localInstance == null) {
            synchronized (ButtonOpenFile.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ButtonOpenFile();
                }
            }
        }
        return localInstance;
    }

    public ButtonOpenFile() {
        super("buttonOpenFile.png", "Открыть документ");
        setVisible(true);
    }

    @Override
    protected void action() {
        if (Table.getInstance().getSelectedRow() < 0) {
            alert("Ошибка!", "Строка не выбрана!");
            return;
        }
        openFile();
    }

    private void openFile() throws NullPointerException {
        String s = Table.getInstance().getFileFromReport();
        if (s.equals("null") || s.equals("")) {
            alert("Ошибка!", "Документ отсутствует!");
            return;
        }
        File file = new File(s);
        Desktop desktop = Desktop.getDesktop();
        if (file.exists()) {
            try {
                desktop.open(file);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
