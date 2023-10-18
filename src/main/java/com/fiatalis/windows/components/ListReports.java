package com.fiatalis.windows.components;

import com.fiatalis.CRUD.entytis.Reports;
import com.fiatalis.windows.modelTable.MainModel;
import com.fiatalis.windows.modelTable.Model;
import com.fiatalis.windows.modelTable.SecondModel;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

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
                    ListReports.getInstance().removeColumn(ListReports.getInstance().getColumnModel().getColumn(0));
                    System.out.println(model.getValueAt(ListReports.getInstance().getSelectedRow(),0));
                    ListReports.getInstance().setModel(new SecondModel());
                }
            }
        });
    }

    public void setEditableModel(Boolean editableModel) {
        model.setEditableModel(editableModel);
    }

    public void deleteRow() {
        model.deleteRow();
    }

    public void addRow(Reports reports) {
        model.addRow(reports);
    }
}