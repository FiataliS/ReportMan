package com.fiatalis.windows.components.center;

import com.fiatalis.CRUD.DAO.ExecutorDAO;
import com.fiatalis.CRUD.Frequency;
import com.fiatalis.entytis.Entity;
import com.fiatalis.entytis.Executor;
import com.fiatalis.entytis.Reports;
import com.fiatalis.windows.components.up.ButtonBack;
import com.fiatalis.windows.components.down.LabelInfo;
import com.fiatalis.windows.components.center.modelTable.ReportModel;
import com.fiatalis.windows.components.center.modelTable.ExecutorModel;
import org.jdesktop.swingx.table.DatePickerCellEditor;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;

public class Table extends JTable {
    private static volatile Table instance;

    public static Table getInstance() {
        Table localInstance = instance;
        if (localInstance == null) {
            synchronized (Table.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Table();
                }
            }
        }
        return localInstance;
    }

    public Table() {
        listeners();
        this.setModel(ReportModel.getInstance());
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        optionTableReport();
    }

    private void listeners() {
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !ReportModel.getInstance().getIsEditable()) {
                    if (Table.this.getModel() instanceof ReportModel) switchModel();
                }
                if (e.getClickCount() == 1 && (Table.getInstance().getModel() instanceof ReportModel)) {
                    Reports reports = (Reports) ReportModel.getInstance().getEntityListFromModel().get(Table.getInstance().getSelectedRow());
                    List<Entity> list = new ExecutorDAO().findAll(reports.getId());
                    LabelInfo.getInstance().setText("<html>Колличество организаций: " + list.size() + "<br>До конца срока осталось: " + "Пока не умею считать! " + "</html>");
                }
            }
        });
    }

    public void setEditableModel(Boolean editableModel) {
        if (this.getModel() instanceof ReportModel) {
            ReportModel r = (ReportModel) this.getModel();
            r.setEditableModel(editableModel);
        } else {
            ExecutorModel s = (ExecutorModel) this.getModel();
            s.setEditableModel(editableModel);
        }
    }

    public void deleteRowEntity() {
        if (this.getModel() instanceof ReportModel) {
            ReportModel r = (ReportModel) this.getModel();
            r.deleteRowEntity(this.getSelectedRow());
        } else {
            ExecutorModel s = (ExecutorModel) this.getModel();
            s.deleteRowEntity(this.getSelectedRow());
        }
    }

    public void addRowEntity() {
        if (this.getModel() instanceof ReportModel) {
            ReportModel r = (ReportModel) this.getModel();
            Reports reports = new Reports();
            reports.setFrequency(Frequency.None);
            r.addRowEntity(reports);
        } else {
            ExecutorModel s = (ExecutorModel) this.getModel();
            s.addRowEntity(new Executor());
        }
    }

    public void switchModel() {
        if (Table.getInstance().getModel() instanceof ReportModel) {
            this.setModel(new ExecutorModel((Reports) ReportModel.getInstance().getEntityListFromModel().get(Table.getInstance().getSelectedRow())));
            optionTableExecutor();
            ButtonBack.getInstance().setVisible(true);
        } else if (Table.getInstance().getModel() instanceof ExecutorModel) {
            this.setModel(ReportModel.getInstance());
            optionTableReport();
            ButtonBack.getInstance().setVisible(false);
        }
    }

    private void optionTableExecutor() {
        this.removeColumn(this.getColumnModel().getColumn(0));
        this.removeColumn(this.getColumnModel().getColumn(4));
        this.getColumnModel().getColumn(0).setCellRenderer(new LineWrapCellRenderer());
        this.getColumnModel().getColumn(1).setCellRenderer(new LineWrapCellRenderer());
        this.getColumnModel().getColumn(1).setMaxWidth(300);
        this.getColumnModel().getColumn(1).setMinWidth(100);
        this.getColumnModel().getColumn(1).setResizable(false);
        this.getColumnModel().getColumn(2).setMaxWidth(150);
        this.getColumnModel().getColumn(2).setMinWidth(150);
        this.getColumnModel().getColumn(2).setResizable(false);
        this.getColumnModel().getColumn(3).setMaxWidth(100);
        this.getColumnModel().getColumn(3).setMinWidth(100);
        this.getColumnModel().getColumn(3).setResizable(false);
    }

    private void optionTableReport() {
        this.removeColumn(this.getColumnModel().getColumn(0));
        this.removeColumn(this.getColumnModel().getColumn(4));
        this.removeColumn(this.getColumnModel().getColumn(4));
        DatePickerCellEditor datePickerCellEditor = new DatePickerCellEditor();
        datePickerCellEditor.setFormats(new SimpleDateFormat("dd.MM.yyyy"));
        this.getColumnModel().getColumn(1).setCellEditor(datePickerCellEditor);
        this.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new ComboBoxFrequencyReport()));
        this.getColumnModel().getColumn(0).setCellRenderer(new LineWrapCellRenderer());
        this.getColumnModel().getColumn(1).setMaxWidth(70);
        this.getColumnModel().getColumn(1).setMinWidth(70);
        this.getColumnModel().getColumn(1).setResizable(false);
        this.getColumnModel().getColumn(2).setMaxWidth(100);
        this.getColumnModel().getColumn(2).setMinWidth(100);
        this.getColumnModel().getColumn(2).setResizable(false);
        this.getColumnModel().getColumn(3).setMaxWidth(100);
        this.getColumnModel().getColumn(3).setMinWidth(100);
        this.getColumnModel().getColumn(3).setResizable(false);
    }

    private static class LineWrapCellRenderer extends JTextArea implements TableCellRenderer {
        LineWrapCellRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
            setOpaque(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
            if (table.getRowHeight(row) != getPreferredSize().height) {
                table.setRowHeight(row, getPreferredSize().height);
            }
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(table.getBackground());
            }
            return this;
        }
    }
}

