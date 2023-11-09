package com.fiatalis.windows.components;

import com.fiatalis.CRUD.entytis.Entity;
import com.fiatalis.windows.MainTable;
import com.fiatalis.windows.components.modelTable.ExecutorModel;
import com.fiatalis.windows.components.modelTable.ReportModel;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class OpenFileButton extends JMenuItem {

    private static volatile OpenFileButton instance;

    public static OpenFileButton getInstance() {
        OpenFileButton localInstance = instance;
        if (localInstance == null) {
            synchronized (OpenFileButton.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new OpenFileButton();
                }
            }
        }
        return localInstance;
    }

    public OpenFileButton() {
        super();
        this.setBorder(new BevelBorder(0));
        Image img = Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("com.fiatalis/image/buttonOpenFile.png"));
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(new ImageIcon(img)), BorderLayout.CENTER);
        this.add(panel);
        this.setVisible(true);
        listeners();
    }

    private void listeners() {
        this.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (MainTable.getInstance().getModel() instanceof ReportModel) {
                    Image img = Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("com.fiatalis/image/deleteRowFiled.png"));
                    final JOptionPane pane = new JOptionPane("Строка не выбрана!", JOptionPane.WARNING_MESSAGE, JOptionPane.OK_OPTION, new ImageIcon(img), new String[]{"OK"});
                    JDialog dialog = pane.createDialog(null, "Ошибка!");
                    if (MainTable.getInstance().getSelectedRow() < 0) {
                        dialog.setVisible(true);
                        return;
                    }
                    openFile(MainTable.getInstance().getSelectedRow());
                } else {
                    int reportId = (int) ((ExecutorModel) MainTable.getInstance().getModel()).getReportId();
                    List<Entity> entityList = ReportModel.getInstance().getEntityListFromModel();
                    for (int i = 0; i < entityList.size(); i++) {
                        if (reportId == entityList.get(i).getId()) {
                            openFile(i);
                            return;
                        }
                    }
                }
            }
        });
    }

    private void openFile(int selectedRow) {
        String s = (String) ReportModel.getInstance().getValueAt(selectedRow, 5);
        if (s.equals("null")) return;
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
