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
import java.util.List;
import java.util.Locale;
import java.util.stream.IntStream;

public class ButtonNewFile extends JMenuItem {

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
        super();
        this.setBorder(new BevelBorder(0));
        this.setToolTipText("Добавить файл");
        Image img = Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("com.fiatalis/image/buttonAddFile.png"));
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel(new ImageIcon(img)), BorderLayout.CENTER);
        this.add(panel);
        this.setVisible(true);
        listeners();
    }

    @Override
    public JToolTip createToolTip() {
        return new CustomJToolTip(this);
    }

    private void listeners() {
        this.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
        });
    }

    private void addFile(int selectedRow) {
        JFileChooser fileChooser = new JFileChooser();
        JFileChooser.setDefaultLocale(new Locale("Russian", "Russia"));
        fileChooser.showOpenDialog(new JFileChooser());
        File file = fileChooser.getSelectedFile();
        if (file != null) ReportModel.getInstance().setValueAt(file.toString(), selectedRow, 5);
    }
}
