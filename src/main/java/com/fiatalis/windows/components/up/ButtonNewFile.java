package com.fiatalis.windows.components.up;

import com.fiatalis.windows.components.center.Table;
import com.fiatalis.windows.components.center.modelTable.ReportModel;

import javax.swing.*;
import java.io.File;
import java.util.Locale;

public class ButtonNewFile extends ButtonMenuItem {

    private static volatile ButtonNewFile instance;

    public static ButtonNewFile getInstance() {
        ButtonNewFile localInstance = instance;
        if (localInstance == null) {
            synchronized (ButtonNewFile.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ButtonNewFile();
                }
            }
        }
        return localInstance;
    }

    public ButtonNewFile() {
        super("buttonAddFile.png", "Добавить документ");
    }

    private void addFile(int selectedRow) {
        JFileChooser fileChooser = new JFileChooser();
        JFileChooser.setDefaultLocale(new Locale("Russian", "Russia"));
        fileChooser.showOpenDialog(new JFileChooser());
        File file = fileChooser.getSelectedFile();
        if (file != null) ReportModel.getInstance().setValueAt(file.toString(), selectedRow, 5);
    }

    @Override
    protected void action() {
        if (Table.getInstance().getModel() instanceof ReportModel) {
            if (Table.getInstance().getSelectedRow() < 0) {
                alert("Ошибка!", "Строка не выбрана!");
                return;
            }
            addFile(Table.getInstance().getSelectedRow());
        } else {
            JFileChooser fileChooser = new JFileChooser();
            JFileChooser.setDefaultLocale(new Locale("Russian", "Russia"));
            fileChooser.showOpenDialog(new JFileChooser());
            File file = fileChooser.getSelectedFile();
            if (file != null) Table.getInstance().setFileURL(file.toString());
        }
    }
}
