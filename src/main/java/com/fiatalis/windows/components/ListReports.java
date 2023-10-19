package com.fiatalis.windows.components;

import com.fiatalis.CRUD.entytis.Entity;
import com.fiatalis.windows.modelTable.MainModel;
import com.fiatalis.windows.modelTable.SecondModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListReports extends JTable {
    private static volatile ListReports instance;

    public static ListReports getInstance() {
        ListReports localInstance = instance;
        if (localInstance == null) {
            synchronized (ListReports.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ListReports();
                }
            }
        }
        return localInstance;
    }

    public ListReports() {
        super();
        this.setModel(MainModel.getInstance());
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.removeColumn(this.getColumnModel().getColumn(0));
        listeners();
    }

    private void listeners() {
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !MainModel.getInstance().isEditable) {
                    if (ListReports.getInstance().getModel() instanceof MainModel) {
                        ListReports.getInstance().setModel(SecondModel.getInstance((Long) MainModel.getInstance().getValueAt(ListReports.getInstance().getSelectedRow(), 0)));
                        ListReports.getInstance().removeColumn(ListReports.getInstance().getColumnModel().getColumn(0));
                        ButtonBack.getInstance().setVisible(true);
                    }
                }
            }
        });
    }

    public void setEditableModel(Boolean editableModel) {

        if (this.getModel() instanceof MainModel) {
            MainModel.getInstance().setEditableModel(editableModel);
        } else {
            SecondModel.getInstance(null).setEditableModel(editableModel);
        }
    }

    public void deleteRow() {
        if (this.getModel() instanceof MainModel) {
            MainModel.getInstance().deleteRow();
        } else {
            SecondModel.getInstance(null).deleteRow();
        }
    }

    public void addRow(Entity entity) {
        if (this.getModel() instanceof MainModel) {
            MainModel.getInstance().addRow(entity);
            MainModel.getInstance().update();
        } else {
            SecondModel.getInstance(null).addRow(entity);
            SecondModel.getInstance(null).update();
        }

    }
}