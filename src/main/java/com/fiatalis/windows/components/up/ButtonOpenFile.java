package com.fiatalis.windows.components.up;

import com.fiatalis.entytis.Entity;
import com.fiatalis.windows.components.center.Table;
import com.fiatalis.windows.components.center.modelTable.ExecutorModel;
import com.fiatalis.windows.components.center.modelTable.ReportModel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

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
        super("buttonOpenFile.png","Открыть документ");
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
            openFile(Table.getInstance().getSelectedRow());
        } else {
            int reportId = (int) ((ExecutorModel) Table.getInstance().getModel()).getReportId();
            List<Entity> entityList = ReportModel.getInstance().getEntityListFromModel();
            IntStream.range(0, entityList.size()).filter(i -> reportId == entityList.get(i).getId()).findFirst().ifPresent(i -> openFile(i));
        }
    }

    private void openFile(int selectedRow) throws NullPointerException {
        String s = (String) ReportModel.getInstance().getValueAt(selectedRow, 5);
        if (s.equals("null")) {
            Image img = Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("com.fiatalis/image/deleteRowFiled.png"));
            final JOptionPane pane = new JOptionPane("Документ отсутствует!", JOptionPane.WARNING_MESSAGE, JOptionPane.OK_OPTION, new ImageIcon(img), new String[]{"OK"});
            JDialog dialog = pane.createDialog(null, "Ошибка!");
            dialog.setVisible(true);
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
