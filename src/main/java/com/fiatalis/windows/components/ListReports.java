package com.fiatalis.windows.components;

import com.fiatalis.CRUD.entytis.Entity;
import com.fiatalis.windows.modelTable.MainModel;
import com.fiatalis.windows.modelTable.Model;
import com.fiatalis.windows.modelTable.SecondModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListReports extends JTable {
    private static volatile ListReports instance;
    private static Model model;

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
        model = MainModel.getInstance();
        this.setModel(model);
        this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //this.setAutoCreateRowSorter(true);
        this.removeColumn(this.getColumnModel().getColumn(0));
        listeners(this);
    }

    private void listeners(JTable table) {
        this.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if (ListReports.getInstance().getModel() instanceof MainModel) {
                        model = new SecondModel();
                        ListReports.getInstance().setModel(model);
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
            SecondModel.getInstance().setEditableModel(editableModel);
        }
    }

    public void deleteRow() {
        if (this.getModel() instanceof MainModel) {
            MainModel.getInstance().deleteRow();
        } else {
            SecondModel.getInstance().deleteRow();
        }
    }

    public void addRow(Entity entity) {
        if (this.getModel() instanceof MainModel) {
            MainModel.getInstance().addRow(entity);
        } else {
            SecondModel.getInstance().addRow(entity);
        }
    }
}