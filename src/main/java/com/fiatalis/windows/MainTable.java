package com.fiatalis.windows;

import com.fiatalis.CRUD.Frequency;
import com.fiatalis.CRUD.entytis.Executor;
import com.fiatalis.CRUD.entytis.Reports;
import com.fiatalis.windows.components.BackButton;
import com.fiatalis.windows.components.FrequencyComboBox;
import com.fiatalis.windows.components.modelTable.ReportModel;
import com.fiatalis.windows.components.modelTable.ExecutorModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainTable extends JTable {
    private static volatile MainTable instance;

    public static MainTable getInstance() {
        MainTable localInstance = instance;
        if (localInstance == null) {
            synchronized (MainTable.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new MainTable();
                }
            }
        }
        return localInstance;
    }

    public MainTable() {
        super();
        listeners();
        this.setModel(ReportModel.getInstance());
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.removeColumn(this.getColumnModel().getColumn(0));
        this.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new FrequencyComboBox()));
    }

    private void listeners() {
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !ReportModel.getInstance().isEditable) {
                    if (MainTable.this.getModel() instanceof ReportModel) switchModel();
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
            r.update();
        } else {
            ExecutorModel s = (ExecutorModel) this.getModel();
            s.deleteRowEntity(this.getSelectedRow());
            s.update();
        }
    }

    public void addRowEntity() {
        if (this.getModel() instanceof ReportModel) {
            ReportModel r = (ReportModel) this.getModel();
            Reports reports = new Reports();
            reports.setFrequency(Frequency.None);
            r.addRowEntity(reports);
            r.update();
        } else {
            ExecutorModel s = (ExecutorModel) this.getModel();
            s.addRowEntity(new Executor());
            s.update();
        }
    }

    public void switchModel() {
        if (MainTable.getInstance().getModel() instanceof ReportModel) {
            this.setModel(ExecutorModel.getInstance((Long) ReportModel.getInstance().getValueAt(MainTable.getInstance().getSelectedRow(), 0)));
            this.removeColumn(this.getColumnModel().getColumn(0));
            BackButton.getInstance().setVisible(true);
        } else if (MainTable.getInstance().getModel() instanceof ExecutorModel) {
            this.setModel(ReportModel.getInstance());
            this.removeColumn(this.getColumnModel().getColumn(0));
            this.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new FrequencyComboBox()));
            BackButton.getInstance().setVisible(false);
        }
    }
}