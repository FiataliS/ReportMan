package com.fiatalis.windows.components.up;

import com.fiatalis.entytis.Entity;
import com.fiatalis.windows.components.center.Table;
import com.fiatalis.windows.components.center.modelTable.ExecutorModel;
import com.fiatalis.windows.components.center.modelTable.ReportModel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

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
        super("buttonAddFile.png","Добавить документ");
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
            Image img = Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("com.fiatalis/image/deleteRowFiled.png"));
            final JOptionPane pane = new JOptionPane("Строка не выбрана!", JOptionPane.WARNING_MESSAGE, JOptionPane.OK_OPTION, new ImageIcon(img), new String[]{"OK"});
            JDialog dialog = pane.createDialog(null, "Ошибка!");
            if (Table.getInstance().getSelectedRow() < 0) {
                dialog.setVisible(true);
                return;
            }
            addFile(Table.getInstance().getSelectedRow());
        } else {
            int reportId = (int) ((ExecutorModel) Table.getInstance().getModel()).getReportId();
            List<Entity> entityList = ReportModel.getInstance().getEntityListFromModel();
            IntStream.range(0, entityList.size()).filter(i -> reportId == entityList.get(i).getId()).findFirst().ifPresent(i -> addFile(i));
        }
    }
}
