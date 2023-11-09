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
import java.util.List;
import java.util.Locale;

public class AddFileButton extends JMenuItem {

    private static volatile AddFileButton instance;

    public static AddFileButton getInstance() {
        AddFileButton localInstance = instance;
        if (localInstance == null) {
            synchronized (AddFileButton.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new AddFileButton();
                }
            }
        }
        return localInstance;
    }

    public AddFileButton() {
        super();
        this.setBorder(new BevelBorder(0));
        Image img = Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("com.fiatalis/image/buttonAddFile.png"));
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
                    addFile(MainTable.getInstance().getSelectedRow());
                } else {
                    int reportId = (int) ((ExecutorModel) MainTable.getInstance().getModel()).getReportId();
                    List<Entity> entityList = ReportModel.getInstance().getEntityListFromModel();
                    for (int i = 0; i < entityList.size(); i++) {
                        if (reportId == entityList.get(i).getId()) {
                            addFile(i);
                            return;
                        }
                    }
                }
            }
        });
    }

    private void addFile(int selectedRow) {
        JFileChooser fileChooser = new JFileChooser();
        JFileChooser.setDefaultLocale(new Locale("Russian", "Russia"));
        fileChooser.showOpenDialog(new JFileChooser());
        File file = fileChooser.getSelectedFile();
        ReportModel.getInstance().setValueAt(file.toString(), selectedRow, 5);
    }
}
